package io.github.harryjhin.common.exception

inline fun valid(value: Boolean, lazyMessage: () -> String) {
    if (!value) {
        val message = lazyMessage()
        throw ValidArgumentException(message)
    }
}

inline fun valid(value: Boolean, lazyMessage: () -> String, vararg args: Any) {
    if (!value) {
        val message = lazyMessage()
        throw ValidArgumentException(message, *args)
    }
}