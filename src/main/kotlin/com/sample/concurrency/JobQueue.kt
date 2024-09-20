package com.sample.concurrency

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.TimeUnit

@Component
class JobQueue {

    private val queue: LinkedBlockingQueue<Runnable> = LinkedBlockingQueue()

    fun offerJob(job: Runnable) {
        queue.offer(job)
    }

    @Scheduled(fixedDelay = 100, timeUnit = TimeUnit.MILLISECONDS)
    fun release() {
        check(queue.isNotEmpty())
        queue.poll().run()
    }
}
