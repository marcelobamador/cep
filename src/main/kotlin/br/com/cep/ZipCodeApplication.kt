package br.com.cep

import br.com.cep.config.ViaZipProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
@EnableConfigurationProperties(ViaZipProperties::class)
open class ZipCodeApplication

fun main(args: Array<String>) {
    runApplication<ZipCodeApplication>(*args)
}
