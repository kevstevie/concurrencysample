package com.sample.concurrency

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.RepeatedTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

@SpringBootTest
class ConcurrencyTest(
    @Autowired private val depositService: DepositService,
    @Autowired private val moneyRepository: MoneyRepository
) {

    @RepeatedTest(100)
    fun 동시_입금시_실패하지_않음() {
        val count = 5
        val threadPool = Executors.newFixedThreadPool(count)
        val latch = CountDownLatch(count)
        val money = moneyRepository.save(Money(money = 0))

        for (i in 1..count) {
            try {
                threadPool.execute {
                    depositService.update(money.id, 100)
                }
            } catch (e: Exception) {

            } finally {
                latch.countDown()
            }
        }

        latch.await()
        Thread.sleep(500)
        threadPool.shutdown()
        val updatedMoney = moneyRepository.findById(money.id)
        assertThat(updatedMoney.money).isEqualTo(100L * count)
    }

    @RepeatedTest(50)
    fun 동시_출금시_0아래로_내려가지_않음() {
        val count = 5
        val threadPool = Executors.newFixedThreadPool(count)
        val latch = CountDownLatch(count)
        val money = moneyRepository.save(Money(money = 1000))

        for (i in 1..count) {
            try {
                threadPool.execute {
                    depositService.update(money.id, -200)
                }
            } catch (e: Exception) {

            } finally {
                latch.countDown()
            }
        }

        latch.await()
        Thread.sleep(500)
        threadPool.shutdown()
        val updatedMoney = moneyRepository.findById(money.id)
        assertThat(updatedMoney.money).isEqualTo(0)
    }
}
