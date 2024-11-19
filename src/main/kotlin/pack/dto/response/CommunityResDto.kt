package pack.dto.response

import java.time.Instant

data class CommunityResDto(
    val id: Int,
    val userId: String,
    val title: String,
    val contents: String,
    val createAt: Instant,
    val updatedAt: Instant,
    val files: List<String> // 파일 경로 리스트
)
