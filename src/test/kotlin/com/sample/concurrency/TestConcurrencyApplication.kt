package com.sample.concurrency

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
    fromApplication<ConcurrencyApplication>().with(TestcontainersConfiguration::class).run(*args)
}
