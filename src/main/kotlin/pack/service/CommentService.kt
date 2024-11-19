package pack.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pack.dto.request.CommentReqDto
import pack.dto.response.CommentResDto
import pack.entity.Comment
import pack.repository.CommentRepository
import pack.repository.CommunityRepository
import java.time.Instant

@Service
class CommentService @Autowired constructor(
    private val commentRepository: CommentRepository,
    private val communityRepository: CommunityRepository
) {

    // 댓글 작성 (대댓글 포함)
    fun addComment(requestDto: CommentReqDto, userId: String): CommentResDto {

        val community = communityRepository.findById(requestDto.communityId)
            .orElseThrow { IllegalArgumentException("Community not found") }

        // 부모 댓글 확인 (대댓글인 경우)
        val parentComment = requestDto.parentId?.let {
            commentRepository.findById(it.toLong())
                .orElseThrow { IllegalArgumentException("Parent comment not found") }
        }

        // 댓글 생성
        val comment = Comment().apply {
            this.community = community
            this.parentId = requestDto.parentId
            this.userId = userId
            this.content = requestDto.content
            this.isSecret = requestDto.isSecret
            this.createAt = Instant.now()
        }

        val savedComment = commentRepository.save(comment)

        return CommentResDto(
            id = savedComment.id!!,
            communityId = community.id!!,
            parentId = savedComment.parentId,
            userId = savedComment.userId!!,
            content = savedComment.content!!,
            createAt = savedComment.createAt!!,
            isSecret = savedComment.isSecret ?: false
        )
    }

    // 특정 댓글의 대댓글 조회
    fun getRepliesByParentId(parentId: Int): List<CommentResDto> {
        val replies = commentRepository.findByParentId(parentId)

        return replies.map { reply ->
            CommentResDto(
                id = reply.id!!,
                communityId = reply.community!!.id!!,
                parentId = reply.parentId,
                userId = reply.userId!!,
                content = reply.content!!,
                createAt = reply.createAt!!,
                isSecret = reply.isSecret ?: false
            )
        }
    }

    // 댓글 수정
    fun updateComment(commentId: Int, userId: String, requestDto: CommentReqDto): CommentResDto {
        val comment = commentRepository.findById(commentId.toLong())
            .orElseThrow { IllegalArgumentException("Comment not found") }

        // 작성자 확인
        if (comment.userId != userId) {
            throw IllegalArgumentException("You are not authorized to update this comment")
        }


        // 댓글 수정 가능한 필드만 업데이트
        comment.content = requestDto.content
        comment.isSecret = requestDto.isSecret

        // 저장
        val updatedComment = commentRepository.save(comment)

        return CommentResDto(
            id = updatedComment.id!!,
            communityId = updatedComment.community!!.id!!,
            parentId = updatedComment.parentId,
            userId = updatedComment.userId!!,
            content = updatedComment.content!!,
            createAt = updatedComment.createAt!!,
            isSecret = updatedComment.isSecret ?: false
        )
    }

    // 댓글 삭제
    fun deleteComment(commentId: Int, userId: String) {
        val comment = commentRepository.findById(commentId.toLong())
            .orElseThrow { IllegalArgumentException("Comment not found") }

        // 작성자 확인
        if (comment.userId != userId) {
            throw IllegalArgumentException("You are not authorized to delete this comment")
        }

        commentRepository.deleteById(commentId.toLong())
    }

    // 특정 커뮤니티의 댓글 조회
    fun getCommentsByCommunityId(communityId: Int): List<CommentResDto> {
        val comments = commentRepository.findByCommunityId(communityId)

        return comments.map { comment ->
            CommentResDto(
                id = comment.id!!,
                communityId = communityId,
                parentId = comment.parentId,
                userId = comment.userId!!,
                content = comment.content!!,
                createAt = comment.createAt!!,
                isSecret = comment.isSecret ?: false
            )
        }
    }
}

