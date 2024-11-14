package pack.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import jakarta.annotation.PostConstruct
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Service
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.security.Key
import java.util.*
import kotlin.NoSuchElementException

@Service
class JwtService {

    private lateinit var key: Key

    @PostConstruct
    fun init() {
        key = Keys.secretKeyFor(SignatureAlgorithm.HS256)
    }

    /*
    JWT 생성
    @param userId
    @return String
     */
    fun createJwt(userId: String): String {
        return Jwts.builder()
            .setHeaderParam("type", "jwt")
            .claim("userId", userId)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + 86400)) // 하루
            .signWith(key)
            .compact()
    }

    /*
    Header에서 Authorization으로 JWT 추출
    @return String
     */
    fun getJwt(): String? {
        val request: HttpServletRequest =
            (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).request
        return request.getHeader("Authorization")
    }

    /*
    JWT에서 userId 추출
    @return String
     */
    fun getUserId(token: String): String {
        val accessToken = token.removePrefix("Bearer ")

        val claims: Jws<Claims> = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(accessToken)

        return claims.body["userId", String::class.java] ?: throw IllegalArgumentException("userId not found in JWT")
    }


}