package com.sample.concurrency

import org.springframework.data.annotation.Id


class Money(
    @Id
    val id: Long = 0,
    var money: Long
) {

    fun update(amount: Long) {
        require(money + amount >= 0)
        money += amount
    }
}
