package br.com.cep.config

import io.swagger.v3.oas.models.OpenAPI
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import io.swagger.v3.oas.models.info.Info

@Configuration
open class OpenApiConfig {

    @Bean
    open fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("CEP Lookup API")
                    .version("1.0.0")
                    .description("API for looking up Brazilian zip codes using ViaCEP")
            )
    }
}