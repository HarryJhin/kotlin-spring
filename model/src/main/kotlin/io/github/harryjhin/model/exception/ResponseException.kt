package io.github.harryjhin.model.exception

abstract class ResponseException : RuntimeException() {
    abstract val responseStatus: Int
    abstract val responseMessage: String
}
