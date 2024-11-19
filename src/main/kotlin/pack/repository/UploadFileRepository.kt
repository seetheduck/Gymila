package pack.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pack.entity.UploadFile

@Repository
interface UploadFileRepository : JpaRepository<UploadFile, Int> {
    fun findByCommunityId(communityId: String): List<UploadFile> // 게시글에 포함된 파일 조회
}