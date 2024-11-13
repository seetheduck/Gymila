package pack.controller

import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pack.entity.UserMaster
import pack.service.UserService

@RestController
@RequestMapping("/mypage")
class MyPageController {

    @Autowired
    private lateinit var userService: UserService

    // 확인용
    @GetMapping("/test")
    fun test() : String {
        return "mypage test request check"
    }

    /*
    로그인한 유저 정보 가져오기
    [GET] /mypage/user-info
    @param HttpServletRequest
    @return UserMaster
    수정 예정!
     */
    @GetMapping("/user-info")
    fun getUserInfo(request : HttpServletRequest) : UserMaster {
        val userId : String = request.getAttribute("userId").toString()
        return userService.getUserInfo(userId)
    }
}