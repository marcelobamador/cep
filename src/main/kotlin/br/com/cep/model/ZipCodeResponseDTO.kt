package br.com.cep.model

import com.fasterxml.jackson.annotation.JsonProperty

data class ZipCodeResponseDTO(
    @JsonProperty("cep")
    val cep: String? = null,

    @JsonProperty("logradouro")
    val street: String? = null,

    @JsonProperty("complemento")
    val complement: String? = null,

    @JsonProperty("bairro")
    val neighborhood: String? = null,

    @JsonProperty("localidade")
    val city: String? = null,

    @JsonProperty("uf")
    val state: String? = null,

    @JsonProperty("ibge")
    val ibgeCode: String? = null,

    @JsonProperty("gia")
    val giaCode: String? = null,

    @JsonProperty("ddd")
    val areaCode: String? = null,

    @JsonProperty("siafi")
    val siafiCode: String? = null
)
