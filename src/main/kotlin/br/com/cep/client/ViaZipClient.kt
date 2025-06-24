package br.com.cep.client

import br.com.cep.exception.ExternalApiException
import br.com.cep.model.ZipCodeResponseDTO
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import org.springframework.web.reactive.function.client.WebClientResponseException
import reactor.core.publisher.Mono
import reactor.util.retry.Retry
import java.time.Duration

@Component
class ViaZipClient(private val webClient: WebClient) {

    fun fetchZipCode(zipCode: String): Mono<ZipCodeResponseDTO> {
        return webClient.get()
            .uri("/ws/{zipCode}/json/", zipCode)
            .retrieve()
            .bodyToMono(ZipCodeResponseDTO::class.java)
            .retryWhen(
                Retry.fixedDelay(3, Duration.ofSeconds(1))
                    .filter { throwable -> throwable is WebClientResponseException || throwable is java.io.IOException }
            )
            .onErrorMap { ex ->
                when (ex) {
                    is WebClientResponseException -> ExternalApiException("Error querying ViaCEP API: ${ex.statusCode}")
                    else -> ExternalApiException("Unexpected error querying ViaCEP API: ${ex.message}")
                }
            }
    }
}