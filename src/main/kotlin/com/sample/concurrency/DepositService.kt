package com.sample.concurrency

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class DepositService(
    private val moneyRepository: MoneyRepository,
    private val concurrencyManager: ConcurrencyManager
) {

    fun update(id: Long, amount: Long) {
        concurrencyManager.getLock { update(id, amount) }
        val money = moneyRepository.findById(id)

        money.update(amount)

        moneyRepository.save(money)
        concurrencyManager.release()
    }

    fun getMoney(id: Long): Money {
        return moneyRepository.findById(id)
    }
}
