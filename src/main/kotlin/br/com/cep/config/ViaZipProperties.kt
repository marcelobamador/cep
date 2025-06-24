package br.com.cep.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "via-cep")
open class ViaZipProperties {
    lateinit var baseUrl: String
}