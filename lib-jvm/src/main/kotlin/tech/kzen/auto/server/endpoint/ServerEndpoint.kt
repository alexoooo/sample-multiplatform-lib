package tech.kzen.auto.server.endpoint

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.router


@Configuration
class ServerEndpoint(
    private val restHandler: RestHandler
) {
    @Suppress("unused")
    @Bean
    fun counterRouter() = router {
        GET("/", restHandler::staticResource)
        GET("/**", restHandler::staticResource)
    }
}