package pack.dto.response

data class LoginResDto(
    val isSuccess : Boolean,
    val token : String?,
    val message : String
)
