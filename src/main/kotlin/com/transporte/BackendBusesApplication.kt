package com.transporte

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["com.transporte"])
class BackendBusesApplication

fun main(args: Array<String>) {
  runApplication<BackendBusesApplication>(*args)
}
