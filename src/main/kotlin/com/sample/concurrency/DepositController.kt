package com.sample.concurrency

import org.springframework.web.bind.annotation.*

@RestController
class DepositController(
    private val depositService: DepositService,
    private val jobQueue: JobQueue
) {

    @PatchMapping("/deposit/{id}")
    fun deposit(
        @RequestBody request: DepositRequest,
        @PathVariable id: Long
    ) {
        depositService.update(id, request.amount)
    }

    @GetMapping("/deposit/{id}")
    fun getMoney(@PathVariable id: Long): Money {
        return depositService.getMoney(id)
    }
}
