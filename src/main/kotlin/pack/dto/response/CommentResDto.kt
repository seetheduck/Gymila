package pack.dto.response

import java.time.Instant

data class CommentResDto(
    val id: Int,            // 댓글 ID
    val communityId: Int,   // 댓글이 속한 커뮤니티 ID
    val parentId: Int?,     // 부모 댓글 ID (대댓글일 경우)
    val userId: String,     // 작성자 ID
    val content: String,    // 댓글 내용
    val createAt: Instant,  // 생성 시간
    val isSecret: Boolean   // 비밀 댓글 여부
)