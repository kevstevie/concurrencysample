package com.sample.concurrency

import org.springframework.data.annotation.Id


class Money(
    @Id
    val id: Long = 0,
    var money: Long
) {

    fun add(amount: Long) {
        money += amount
    }

    fun withdraw(amount: Long) {
        require(money >= amount)
        money -= amount
    }
}
