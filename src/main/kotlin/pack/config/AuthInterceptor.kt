package pack.config

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import pack.service.JwtService

@Component
class AuthInterceptor: HandlerInterceptor {

    @Autowired
    private lateinit var jwtService: JwtService

    // 컨트롤러 실행 전 JWT 인증 확인
    // @NoAuth 애노테이션이 있으면 인증 생략
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val hm = handler as HandlerMethod
        val check: Boolean = hm.hasMethodAnnotation(NoAuth::class.java)
        if (check) return true

        return try {
            val userIdInJwt = jwtService.getUserId()  // JWT에서 사용자 ID 추출
            request.setAttribute("userId", userIdInJwt)  // 사용자 ID를 요청 속성에 추가
            true
        } catch (e: Exception) {
            println(e.message)
            response.status = HttpServletResponse.SC_UNAUTHORIZED  // 인증 실패 시 401 응답
            false
        }
    }

}