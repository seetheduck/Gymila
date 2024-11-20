package pack.dto.response

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL) // null이면 반환값에 미포함
data class AccountResDto<T>( // 사용자 계정 관련 요청시 반환
    val status: Int,
    val isSuccess: Boolean,
    val message: String,
    val data: T? = null // 처리 데이터
)
