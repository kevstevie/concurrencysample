package com.sample.concurrency

import org.springframework.data.repository.Repository

interface MoneyRepository : Repository<Money, Long> {

    fun findById(id: Long): Money

    fun save(money: Money): Money
}
