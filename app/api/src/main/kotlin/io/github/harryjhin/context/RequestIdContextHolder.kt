package io.github.harryjhin.context

import org.springframework.core.NamedThreadLocal

object RequestIdContextHolder {

    private val requestId = NamedThreadLocal<String>("Request Id")

    fun setRequestId(id: String) {
        requestId.set(id)
    }

    fun getRequestId(): String {
        return requestId.get()
    }

    fun clear() {
        requestId.remove()
    }
}
