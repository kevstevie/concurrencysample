package com.sample.concurrency

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class ConcurrencyApplication

fun main(args: Array<String>) {
    runApplication<ConcurrencyApplication>(*args)
}
