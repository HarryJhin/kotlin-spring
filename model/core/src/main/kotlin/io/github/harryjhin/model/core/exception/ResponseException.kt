package io.github.harryjhin.model.core.exception

abstract class ResponseException : RuntimeException() {
    abstract val responseStatus: Int
    abstract val responseMessage: String
}
