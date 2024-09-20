package com.sample.concurrency

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class DepositService(
    private val moneyRepository: MoneyRepository,
    private val concurrencyManager: ConcurrencyManager
) {

    fun add(id: Long, amount: Long) {
        concurrencyManager.getLock { add(id, amount) }
        val money = moneyRepository.findById(id)

        money.add(amount)

        moneyRepository.save(money)
        concurrencyManager.release()
    }

    fun withdraw(id: Long, amount: Long) {
        concurrencyManager.getLock { withdraw(id, amount) }
        val money = moneyRepository.findById(id)

        money.withdraw(amount)


        moneyRepository.save(money)
        concurrencyManager.release()
    }
}
