package com.sample.concurrency

import org.springframework.dao.ConcurrencyFailureException
import org.springframework.stereotype.Component
import java.util.concurrent.atomic.AtomicBoolean

@Component
class ConcurrencyManager(private val jobQueue: JobQueue) {

    private val flag: AtomicBoolean = AtomicBoolean(false)

    fun getLock(job: Runnable) {
        if (flag.compareAndSet(false, true).not()) {
            jobQueue.offerJob(job)
            throw ConcurrencyFailureException("Concurrent access")
        }
    }

    fun release() {
        flag.set(false)
        jobQueue.release()
    }
}
