package br.com.cep

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
import java.time.Duration
import java.util.concurrent.TimeUnit

@SpringBootApplication(exclude = [
    DataSourceAutoConfiguration::class,
    HibernateJpaAutoConfiguration::class
])
open class ZipCodeApplication {

    @Bean
    open fun webClient(): WebClient {
        val httpClient = HttpClient.create()
            .responseTimeout(Duration.ofSeconds(5))
            .doOnConnected { conn ->
                conn.addHandlerLast(ReadTimeoutHandler(5, TimeUnit.SECONDS))
                conn.addHandlerLast(WriteTimeoutHandler(5, TimeUnit.SECONDS))
            }

        return WebClient.builder()
            .baseUrl("https://viacep.com.br")
            .clientConnector(ReactorClientHttpConnector(httpClient))
            .build()
    }
}

fun main(args: Array<String>) {
    runApplication<ZipCodeApplication>(*args)
}
