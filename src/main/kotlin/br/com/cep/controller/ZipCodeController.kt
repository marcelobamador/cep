package br.com.cep.controller

import br.com.cep.model.ZipCodeResponseDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import br.com.cep.service.ZipService
import jakarta.validation.Valid
import jakarta.validation.constraints.Pattern
import org.springframework.http.MediaType
import org.springframework.validation.annotation.Validated
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/zipcode")
@Validated
class ZipCodeController(private val zipCodeService: ZipService) {

    @GetMapping("/{zipCode}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getZipCode(
        @PathVariable
        @Valid
        @Pattern(regexp = "\\d{8}", message = "ZipCode must be exactly 8 digits")
        zipCode: String
    ): Mono<ZipCodeResponseDTO> {
        return zipCodeService.getZipCodeInfo(zipCode)
    }
}