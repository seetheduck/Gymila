package pack.config

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import pack.dto.response.AccountResDto

@RestControllerAdvice
class CustomExceptionHandler {

    @ExceptionHandler(AccountException::class) // AccountException 맵핑
    protected fun accountException(e: AccountException): ResponseEntity<AccountResDto<Any>> {
        return ResponseEntity(AccountResDto(e.accountResCode.status.value(), false, e.accountResCode.message), e.accountResCode.status)
    }
}