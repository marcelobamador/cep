package br.com.cep.config

import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import reactor.util.retry.Retry
import java.time.Duration
import java.util.concurrent.TimeUnit

@Configuration
open class WebClientConfig {

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
            .filter(logRequest())
            .filter(logResponse())
            .build()
    }

    private fun logRequest(): ExchangeFilterFunction {
        return ExchangeFilterFunction.ofRequestProcessor { clientRequest ->
            println("Request: ${clientRequest.method()} ${clientRequest.url()}")
            println("Headers: ${clientRequest.headers()}")
            reactor.core.publisher.Mono.just(clientRequest)
        }
    }

    private fun logResponse(): ExchangeFilterFunction {
        return ExchangeFilterFunction.ofResponseProcessor { clientResponse ->
            println("Response status: ${clientResponse.statusCode()}")
            reactor.core.publisher.Mono.just(clientResponse)
        }
    }
}