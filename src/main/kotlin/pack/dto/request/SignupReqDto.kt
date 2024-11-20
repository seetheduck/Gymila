package pack.dto.request

import java.time.Instant

data class SignupReqDto(
    // userId, password, nickname, createdAt
    val userId: String,
    val password: String,
    val nickName: String,
    val createdAt: Instant,
    val idCheckPassed: Boolean
)