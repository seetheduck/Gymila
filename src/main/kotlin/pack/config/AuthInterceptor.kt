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

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val hm = handler as HandlerMethod
        val check: Boolean = hm.hasMethodAnnotation(NoAuth::class.java)
        if(check) return true

        return try {
            val userIdInJwt = jwtService.getUserId()
            request.setAttribute("userId", userIdInJwt)
            true
        } catch (e: Exception){
            //
            println(e.message)
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            false
        }
    }

}