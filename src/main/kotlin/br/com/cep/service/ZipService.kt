package br.com.cep.service

import br.com.cep.client.ViaZipClient
import br.com.cep.model.ZipCodeResponseDTO
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
open class ZipService(private val viaCepClient: ViaZipClient) {

    open fun getZipCodeInfo(zipCode: String): Mono<ZipCodeResponseDTO> {
        return viaCepClient.fetchZipCode(zipCode)
    }
}