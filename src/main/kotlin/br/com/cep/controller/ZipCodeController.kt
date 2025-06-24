package br.com.cep.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import br.com.cep.service.ZipService

@RestController
@RequestMapping("/cep")
class ZipCodeController(private val cepService: ZipService) {

    @GetMapping("/{cep}")
    fun getZipCode(@PathVariable cep: String): ResponseEntity<Any> {
        if (!cep.matches(Regex("\\d{8}"))) {
            return ResponseEntity.badRequest().body("CEP inválido. Informe apenas 8 dígitos numéricos.")
        }
        val response = cepService.searchCep(cep)
        return ResponseEntity.ok(response)
    }
}