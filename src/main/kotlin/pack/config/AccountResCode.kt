package pack.config

import org.springframework.http.HttpStatus

enum class AccountResCode(val status: HttpStatus, val message: String) {
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "bad request"),
    ID_AVAILABLE_CHECK_ABSENT(HttpStatus.BAD_REQUEST, "ID check absent"),
    USER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "user with this ID already exists"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "user with this ID does not exist"),
    ID_MISMATCH(HttpStatus.NOT_FOUND, "check your ID"),
    PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST, "check your password")
}