package io.github.harryjhin.model.exception

abstract class ResponseException : RuntimeException() {
    abstract override val message: String
    abstract val responseStatus: Int
    abstract val responseMessage: String
}