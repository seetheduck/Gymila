package pack

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GymilaApplication

fun main(args: Array<String>) {
    runApplication<GymilaApplication>(*args)
}
