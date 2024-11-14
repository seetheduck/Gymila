package pack.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pack.config.NoAuth

@RestController
@RequestMapping("/sign-up")
class SignUpController {

    // 확인용
    @NoAuth
    @GetMapping("/test")
    fun test() : String {
        return "sign-up test request check"
    }
}