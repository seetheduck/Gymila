package pack.service

import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import pack.config.AccountException
import pack.config.AccountResCode
import pack.dto.request.SignupReqDto
import pack.dto.request.UserUpdateReqDto
import pack.dto.response.LoginResDto
import pack.dto.response.AccountResDto
import pack.entity.UserMaster
import pack.repository.UserMasterRepository

@Service
class UserService {

    @Autowired
    private lateinit var userMasterRepository: UserMasterRepository

    @Autowired
    private lateinit var jwtService: JwtService

    // 로그인
    fun login(userId: String, password: String): LoginResDto {
        val user = userMasterRepository.findByUserId(userId)
        return user?.let {
            // password check
            if (it.password.equals(password)) {
                val token = jwtService.createJwt(userId)
                LoginResDto(true, token, "login successful")
            } else {
                //LoginResDto(false, null, "check your password")
                throw AccountException(AccountResCode.PASSWORD_MISMATCH)
            }
            //} ?: LoginResDto(false, null, "check your id")
        } ?: throw AccountException(AccountResCode.ID_MISMATCH)
    }

    // 사용자 정보 조회
    fun getUserInfo(userId: String): UserMaster {
        //return userMasterRepository.findByUserId(userId) ?: throw NoSuchElementException()
        return userMasterRepository.findByUserId(userId) ?: throw AccountException(AccountResCode.USER_NOT_FOUND)
    }

    // 회원가입 시 ID 중복 여부 체크
    fun isUserExists(userId: String): Boolean {
        return userMasterRepository.findByUserId(userId) == null
    }

    // 회원가입
    fun createUser(signupReqDto: SignupReqDto): AccountResDto<SignupReqDto> {
        // ID 중복체크 미수행 시
        if (!signupReqDto.idCheckPassed) throw AccountException(AccountResCode.ID_AVAILABLE_CHECK_ABSENT)

        // (예를 들어) ID 중복체크 한 후에 ID 변경한 경우
        if (userMasterRepository.findByUserId(signupReqDto.userId) != null) throw AccountException(AccountResCode.USER_ALREADY_EXISTS)

        //
        val user = UserMaster().apply {
            // userId  혼동 방지위해 this  명시
            this.userId = signupReqDto.userId
            password = signupReqDto.password
            nickname = signupReqDto.nickName
            createDate = signupReqDto.createdAt
        }

        userMasterRepository.save(user)

        return AccountResDto(HttpStatus.OK.value(), true, "sign up successful", signupReqDto)

    }

    // 회원 탈퇴
    @Transactional
    fun deleteUser(userId: String): AccountResDto<Any> {
        // 해당 ID 유저 미존재
        if (isUserExists(userId)) throw AccountException(AccountResCode.USER_NOT_FOUND)

        userMasterRepository.deleteByUserId(userId)
        return AccountResDto(HttpStatus.OK.value(), true, "successfully deleted")

    }

    // 회원 정보 수정
    @Transactional
    fun updateUser(userId: String, userUpdateReqDto: UserUpdateReqDto): AccountResDto<UserUpdateReqDto> {
        val user =
            userMasterRepository.findByUserId(userId) ?: throw AccountException(AccountResCode.USER_ALREADY_EXISTS)

        // 값이 다를 때만 수정
        if (!user.password.equals(userUpdateReqDto.password)) {
            user.updatePassword(userUpdateReqDto.password)
        }
        if (!user.nickname.equals(userUpdateReqDto.nickname)) {
            user.updateNickname(userUpdateReqDto.nickname)
        }

        // 수정 날짜 업데이트
        user.updateUpdateDate()

        userMasterRepository.save(user)
        return AccountResDto(HttpStatus.OK.value(), true, "successfully updated", userUpdateReqDto)
    }

}
