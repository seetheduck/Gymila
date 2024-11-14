package pack.config

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import pack.service.JwtService

@Component
class AuthInterceptor @Autowired constructor(
    private val jwtService: JwtService
) : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val requestUri = request.requestURI

        // Swagger 관련 요청은 인증을 생략
        if (requestUri.startsWith("/swagger") || requestUri.startsWith("/api-docs") ||
            requestUri.startsWith("/swagger-ui") || requestUri.startsWith("/swagger-resources")
        ) {
            return true
        }

        // Handler가 HandlerMethod가 아닌 경우 인증 생략
        if (handler !is HandlerMethod) return true

        // @NoAuth 애노테이션이 있는 경우 인증 생략
        if (handler.hasMethodAnnotation(NoAuth::class.java)) return true

        // Authorization 헤더에서 JWT 토큰 추출
        val authHeader = request.getHeader("Authorization").orEmpty()

        return if (authHeader.startsWith("Bearer ")) {
            // Bearer 토큰이 있는 경우만 처리
            val token = authHeader.removePrefix("Bearer ")
            try {
                // 토큰에서 userId 추출 및 요청 속성에 추가
                val userId = jwtService.getUserId(token)
                request.setAttribute("userId", userId)
                true  // 인증 성공
            } catch (e: Exception) {
                // JWT 검증 실패 시 401 상태 반환
                println(e.message)
                response.status = HttpServletResponse.SC_UNAUTHORIZED
                false
            }
        } else {
            // Bearer 토큰이 없는 경우 401 상태 반환
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            false
        }
    }

}
