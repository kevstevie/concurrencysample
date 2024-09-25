package com.sample.concurrency

import org.springframework.stereotype.Component
import java.util.concurrent.LinkedBlockingQueue

@Component
class JobQueue {

    private val queue: LinkedBlockingQueue<Runnable> = LinkedBlockingQueue()

    fun offerJob(job: Runnable) {
        queue.offer(job)
    }

    //    @Scheduled(fixedDelay = 100, timeUnit = TimeUnit.MILLISECONDS)
    fun release() {
        check(queue.isNotEmpty())
        queue.poll().run()
    }
}
