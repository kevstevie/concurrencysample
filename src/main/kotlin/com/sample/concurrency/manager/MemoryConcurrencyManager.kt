package com.sample.concurrency.manager

import com.sample.concurrency.JobQueue
import java.util.concurrent.atomic.AtomicBoolean

class MemoryConcurrencyManager(private val jobQueue: JobQueue) : ConcurrencyManager {

    private val flag: AtomicBoolean = AtomicBoolean(false)

    override fun getLock(): Boolean {
        return flag.compareAndSet(false, true)
    }

    override fun release() {
        flag.set(false)
    }
}
