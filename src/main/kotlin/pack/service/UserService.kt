package pack.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pack.dto.response.LoginResDto
import pack.entity.UserMaster
import pack.repository.UserMasterRepository

@Service
class UserService {

    @Autowired
    private lateinit var userMasterRepository: UserMasterRepository

    @Autowired
    private lateinit var jwtService: JwtService

    fun login(userId: String, password: String): LoginResDto {
        val user = userMasterRepository.findByUserId(userId)
        return user?.let {
            // password check
            if (it.password.equals(password)) {
                val token = jwtService.createJwt(userId)
                LoginResDto(true, token, "login successful")
            } else {
                LoginResDto(false, null, "check your password")
            }
        } ?: LoginResDto(false, null, "check your id")
    }

    fun getUserInfo(userId: String): UserMaster {
        return userMasterRepository.findByUserId(userId) ?: throw NoSuchElementException()
    }
}
