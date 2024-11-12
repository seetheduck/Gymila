package pack.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {  // Swagger

    companion object {
        private const val API_NAME = "Gymila API"
        private const val API_VERSION = "0.0.1"
        private const val API_DESCRIPTION = "Gymila API 명세서"
    }

    @Bean
    fun openAPI(): OpenAPI {
        val info = Info()
            .version(API_VERSION) // 버전
            .title(API_NAME)      // 이름
            .description(API_DESCRIPTION) // 설명
        return OpenAPI()
            .info(info)
    }
}
