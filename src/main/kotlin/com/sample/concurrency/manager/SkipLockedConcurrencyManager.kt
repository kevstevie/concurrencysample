package com.sample.concurrency.manager

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component

@Component
class SkipLockedConcurrencyManager(private val jdbcTemplate: JdbcTemplate) : ConcurrencyManager {

    override fun getLock(): Boolean {
        val q = jdbcTemplate.query("select * from conc for update skip locked") { rs, _ ->
            rs.next()
        }

        return q.isNotEmpty()
    }

    override fun release() {
    }
}
