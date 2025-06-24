package br.com.cep

import br.com.cep.config.ViaZipProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
@EnableCaching
@EnableConfigurationProperties(ViaZipProperties::class)
open class ZipCodeApplication

fun main(args: Array<String>) {
    runApplication<ZipCodeApplication>(*args)
}
