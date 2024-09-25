package com.sample.concurrency

import org.springframework.stereotype.Component
import java.util.concurrent.atomic.AtomicBoolean

@Component
class ConcurrencyManager(private val jobQueue: JobQueue) {

    private val flag: AtomicBoolean = AtomicBoolean(false)

    fun getLock(): Boolean {
        return flag.compareAndSet(false, true)
    }

    fun release() {
        flag.set(false)
    }
}
