package br.com.cep.client

import br.com.cep.exception.ExternalApiException
import br.com.cep.model.ZipCodeResponseDTO
import org.mockito.Mockito
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException
import org.testng.annotations.Test
import reactor.core.publisher.Mono
import reactor.test.StepVerifier


class ViaZipClientTest {
    private val webClient = Mockito.mock(WebClient::class.java, Mockito.RETURNS_DEEP_STUBS)
    private val client = ViaZipClient(webClient)

    @Test
    fun `fetchZipCode should return ZipCodeResponseDTO on success`() {
        val zipCode = "01001000"
        val expectedResponse = ZipCodeResponseDTO(cep = "01001-000")

        Mockito.`when`(
            webClient.get()
                .uri("/ws/{zipCode}/json/", zipCode)
                .retrieve()
                .bodyToMono(ZipCodeResponseDTO::class.java)
        ).thenReturn(Mono.just(expectedResponse))

        val resultMono = client.fetchZipCode(zipCode)

        StepVerifier.create(resultMono)
            .expectNextMatches { it.cep == expectedResponse.cep }
            .verifyComplete()
    }

    @Test
    fun `fetchZipCode should error with ExternalApiException on WebClientResponseException`() {
        val zipCode = "00000000"
        val exception = WebClientResponseException.create(404, "Not Found", null, null, null)

        Mockito.`when`(
            webClient.get()
                .uri("/ws/{zipCode}/json/", zipCode)
                .retrieve()
                .bodyToMono(ZipCodeResponseDTO::class.java)
        ).thenReturn(Mono.error(exception))

        val resultMono = client.fetchZipCode(zipCode)

        StepVerifier.create(resultMono)
            .expectErrorMatches { it is ExternalApiException }
            .verify()
    }
}