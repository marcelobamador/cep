package br.com.cep

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
open class ZipCodeApplication

fun main(args: Array<String>) {
    runApplication<ZipCodeApplication>(*args)
}
