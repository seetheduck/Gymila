package pack.dto.request

data class CommentReqDto(
    val communityId: Int,   // 댓글이 속한 커뮤니티 ID
    val parentId: Int?,     // 부모 댓글 ID (대댓글일 경우)
    //val userId: String,     // 작성자 ID
    val content: String,    // 댓글 내용
    val isSecret: Boolean = false // 비밀 댓글 여부 (기본값: 공개)
)
