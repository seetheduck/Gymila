package pack.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import pack.dto.request.CommunityReqDto
import pack.dto.response.CommunityResDto
import pack.entity.Community
import pack.entity.UploadFile
import pack.repository.CommunityRepository
import pack.repository.UploadFileRepository
import java.io.File
import java.io.IOException
import java.time.Instant
import java.util.UUID

@Service
class CommunityService @Autowired constructor(
    private val communityRepository: CommunityRepository,
    private val uploadFileRepository: UploadFileRepository
) {

    fun createPost(userId: String, requestDto: CommunityReqDto, files: List<MultipartFile>?): CommunityResDto {
        val uploadDir = "C:/kotlin/Gymila/uploads/" // 파일 저장 디렉토리 설정

        // Community 엔티티 생성 및 저장
        val community = Community().apply {
            this.userId = userId
            title = requestDto.title
            contents = requestDto.contents
        }
        val savedCommunity = communityRepository.save(community)

        // 업로드 폴더 생성 (존재하지 않으면 생성)
        val dir = File(uploadDir)
        if (!dir.exists()) {
            dir.mkdirs()
        }

        // 파일 저장 로직
        val filePaths = files?.map { file ->
            val fileName = UUID.randomUUID().toString() + "_" + file.originalFilename // 고유한 파일 이름 생성
            val filePath = "$uploadDir$fileName" // 전체 파일 경로 생성

            // 실제 파일 저장
            try {
                file.transferTo(File(filePath))
            } catch (e: IOException) {
                throw RuntimeException("파일 저장에 실패했습니다: ${e.message}", e)
            }

            // UploadFile 엔티티 저장
            UploadFile().apply {
                communityId = savedCommunity.id.toString()
                path = filePath
            }.also { uploadFileRepository.save(it) }

            filePath
        } ?: emptyList()

        // CommunityResDto 반환
        return CommunityResDto(
            id = savedCommunity.id!!,
            userId = savedCommunity.userId!!,
            title = savedCommunity.title!!,
            contents = savedCommunity.contents!!,
            createAt = savedCommunity.createAt!!,
            updatedAt = savedCommunity.updatedAt!!,
            files = filePaths
        )
    }

    fun updatePost(id: Int, userId: String, requestDto: CommunityReqDto, files: List<MultipartFile>?): CommunityResDto {
        // 게시물 조회
        val community = communityRepository.findById(id).orElseThrow { IllegalArgumentException("Post not found") }

        // 작성자 확인
        if (community.userId != userId) {
            println("err")
        }

        community.title = requestDto.title
        community.contents = requestDto.contents
        community.updatedAt = Instant.now()
        val updatedCommunity = communityRepository.save(community)

        val uploadDir = "C:/kotlin/Gymila/uploads/"

        // 파일 처리
        val filePaths: List<String> = if (files != null && files.isNotEmpty()) {
            // 기존 파일 삭제
            val existingFiles = uploadFileRepository.findByCommunityId(id.toString())
            existingFiles.forEach {
                val file = File(it.path!!)
                if (file.exists()) file.delete() // 파일 삭제
                uploadFileRepository.delete(it) // DB 레코드 삭제
            }

            // 새 파일 저장
            files.map { file ->
                val fileName = UUID.randomUUID().toString() + "_" + file.originalFilename
                val filePath = "$uploadDir$fileName"

                // 폴더 생성 (없을 경우)
                val dir = File(uploadDir)
                if (!dir.exists()) dir.mkdirs()

                try {
                    file.transferTo(File(filePath))
                } catch (e: IOException) {
                    throw RuntimeException("파일 저장에 실패했습니다: ${e.message}", e)
                }

                // 새 파일 정보를 DB에 저장
                UploadFile().apply {
                    communityId = updatedCommunity.id.toString()
                    path = filePath
                }.also { uploadFileRepository.save(it) }

                filePath
            }
        } else {
            // 기존 파일 유지
            uploadFileRepository.findByCommunityId(id.toString()).map { it.path!! }
        }

        // 응답 생성
        return CommunityResDto(
            id = updatedCommunity.id!!,
            userId = updatedCommunity.userId!!,
            title = updatedCommunity.title!!,
            contents = updatedCommunity.contents!!,
            createAt = updatedCommunity.createAt!!,
            updatedAt = updatedCommunity.updatedAt!!,
            files = filePaths
        )
    }


    fun deletePost(id: Int, userId: String) {
        // 게시물 조회
        val community = communityRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Post not found") }

        // 작성자 확인
        if (community.userId != userId) {
            println("err")
        }

        // 게시글에 속한 파일도 함께 삭제
        uploadFileRepository.findByCommunityId(id.toString()).forEach {
            uploadFileRepository.delete(it)
        }
        communityRepository.deleteById(id)
    }

    fun searchPosts(title: String, page: Int, size: Int): Page<CommunityResDto> {
        val pageable = PageRequest.of(page, size)
        return communityRepository.findByTitleContaining(title, pageable).map { community ->
            val files = uploadFileRepository.findByCommunityId(community.id.toString()).map { it.path!! }
            CommunityResDto(
                id = community.id!!,
                userId = community.userId!!,
                title = community.title!!,
                contents = community.contents!!,
                createAt = community.createAt!!,
                updatedAt = community.updatedAt!!,
                files = files
            )
        }
    }

    fun getAllPosts(page: Int, size: Int): Page<CommunityResDto> {
        val pageable = PageRequest.of(page, size)
        return communityRepository.findAll(pageable).map { post ->
            val files = uploadFileRepository.findByCommunityId(post.id.toString()).map { it.path!! }
            CommunityResDto(
                id = post.id!!,
                userId = post.userId!!,
                title = post.title!!,
                contents = post.contents!!,
                createAt = post.createAt!!,
                updatedAt = post.updatedAt!!,
                files = files
            )
        }
    }
}