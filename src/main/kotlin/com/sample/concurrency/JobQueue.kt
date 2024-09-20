package com.sample.concurrency

import org.springframework.stereotype.Component
import java.util.*

@Component
class JobQueue {

    private val queue: Queue<Runnable> = LinkedList()

    fun offerJob(job: Runnable) {
        queue.offer(job)
    }

    fun release() {
        while (queue.isNotEmpty()) {
            queue.poll().run()
        }
    }
}
