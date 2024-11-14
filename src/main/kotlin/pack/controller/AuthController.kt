package pack.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pack.config.NoAuth
import pack.dto.request.LoginReqDto
import pack.dto.response.LoginResDto
import pack.service.UserService

@RestController
@RequestMapping("/auth")
class AuthController {

    @Autowired
    private lateinit var userService: UserService

    // 확인용
    @GetMapping("/test")
    fun test() : String {
        return "auth test request check"
    }

    /*
    유저 로그인
    [POST] /auth/login
    @return LoginResDto
     */
    @NoAuth
    @PostMapping("/login")
    fun login(@RequestBody loginReqDto: LoginReqDto) : ResponseEntity<LoginResDto> {
        val response = userService.login(loginReqDto.userId, loginReqDto.password)
        return if (response.isSuccess){
            ResponseEntity.ok(response)
        } else {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response)
        }
    }
}