package pack.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pack.config.NoAuth
import pack.dto.request.LoginReqDto
import pack.dto.response.LoginResDto
import pack.service.UserService

@Tag(name = "Auth API", description = "APIs related to authentication")
@RestController
@RequestMapping("/auth")
class AuthController {

    @Autowired
    private lateinit var userService: UserService

    /*
    유저 로그인
    [POST] /auth/login
    @return LoginResDto
     */
    @NoAuth
    @PostMapping("/login")
    @Operation(summary = "User login")
    fun login(
        @Parameter(required = true, description = "user ID, password")
        @RequestBody loginReqDto: LoginReqDto
    ): ResponseEntity<LoginResDto> {
        val response = userService.login(loginReqDto.userId, loginReqDto.password)
        return ResponseEntity.ok(response)
    }
}