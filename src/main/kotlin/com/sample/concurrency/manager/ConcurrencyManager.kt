package com.sample.concurrency.manager

interface ConcurrencyManager {

    fun getLock(): Boolean

    fun release()
}
