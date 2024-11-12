package pack

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class GymilaApplication

fun main(args: Array<String>) {
    runApplication<GymilaApplication>(*args)
}
