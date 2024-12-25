package io.github.harryjhin.advice

import io.github.harryjhin.context.RequestIdContextHolder
import io.github.harryjhin.model.exception.ResponseException
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ControllerAdvice {

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @ExceptionHandler(ResponseException::class)
    fun handleServerResponseException(
        request: HttpServletRequest,
        e: ResponseException,
    ): String {
        logger.error("${RequestIdContextHolder.getRequestId()} ${e.responseStatus} ${e.message}")
        return "${RequestIdContextHolder.getRequestId()} ${e.responseStatus} ${e.responseMessage}"
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleException(
        request: HttpServletRequest,
        e: Exception,
    ): String {
        logger.error("${RequestIdContextHolder.getRequestId()} ${e.message}")
        return "${RequestIdContextHolder.getRequestId()} ${e.message}"
    }
}
