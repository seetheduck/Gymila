package pack.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import pack.config.AccountException
import pack.config.AccountResCode
import pack.config.NoAuth
import pack.dto.request.SignupReqDto
import pack.dto.request.UserUpdateReqDto
import pack.dto.response.AccountResDto
import pack.service.UserService

@Tag(name = "Account API", description = "APIs related to user account")
@RestController
@RequestMapping("/account")
class AccountController @Autowired constructor(
    private val userService: UserService
) {

    /*
    ID 중복 여부 체크
    [GET] /account/sign-up/check-id
    @param String
    @return ResponseEntity<AccountResDto>
     */
    @NoAuth
    @GetMapping("/sign-up/check-id")
    @Operation(summary = "ID check", description = "check user ID is available")
    //@ApiResponses
    fun checkId(
        @Parameter(required = true, description = "user inputted ID")
        @RequestParam userId: String
    ): ResponseEntity<AccountResDto<String>> {
        val user = userService.isUserExists(userId)

        // 해당 ID 유저 이미 존재
        if (!user) throw AccountException(AccountResCode.USER_ALREADY_EXISTS)

        return ResponseEntity.ok(AccountResDto(HttpStatus.OK.value(), true, "userId available", userId))
    }

    /*
    회원 생성
    [POST] /account/sign-up
    @param SignupReqDto
    @return ResponseEntity<AccountResDto<SignupReqDto>>
     */
    @NoAuth
    @PostMapping("/sign-up")
    @Operation(summary = "User Registration")
    fun createUser(
        @Parameter(required = true, description = "user inputted data")
        @RequestBody signupReqDto: SignupReqDto
    ): ResponseEntity<AccountResDto<SignupReqDto>> {
        val response = userService.createUser(signupReqDto)
        return ResponseEntity.ok(response)
    }

    /*
    회원 탈퇴
    [DELETE] /account/delete-user
    @param String
    @return ResponseEntity<AccountResDto<Any>>
     */
    @DeleteMapping("/delete-user")
    @Operation(summary = "User Deletion")
    fun deleteUser(request: HttpServletRequest): ResponseEntity<AccountResDto<Any>> {
        val currentUser = request.getAttribute("userId").toString()
        val response = userService.deleteUser(currentUser)
        return ResponseEntity.ok(response)
    }

    /*
    회원 정보 수정
    [PUT] /account/update-user
    @param HttpServletRequest, UserUpdateReqDto
    @return ResponseEntity<AccountResDto<UserUpdateReqDto>>
     */
    @PutMapping("update-user")
    @Operation(summary = "Update User Info")
    fun updateUser(
        @Parameter(required = true, description = "user info to be updated")
        @RequestBody userUpdateReqDto: UserUpdateReqDto,
        request: HttpServletRequest
    ): ResponseEntity<AccountResDto<UserUpdateReqDto>> {
        val userId = request.getAttribute("userId").toString()
        val response = userService.updateUser(userId, userUpdateReqDto)
        return ResponseEntity.ok(response)
    }
}