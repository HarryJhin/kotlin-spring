package io.github.harryjhin.advice

import io.github.harryjhin.context.RequestIdContextHolder
import io.github.harryjhin.common.exception.ResponseException
import io.github.harryjhin.common.exception.ValidArgumentException
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.MessageSource
import org.springframework.core.NestedRuntimeException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ControllerAdvice(
    private val messageSource: MessageSource,
) {

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @ExceptionHandler(ResponseException::class)
    fun handleServerResponseException(
        request: HttpServletRequest,
        e: ResponseException,
    ): String {
        logger.error("${RequestIdContextHolder.getRequestId()} ${e.responseStatus} ${e.message}")
        return "${RequestIdContextHolder.getRequestId()} ${e.responseStatus} ${e.responseMessage}"
    }

    @ExceptionHandler(ValidArgumentException::class)
    fun handleValidArgumentException(
        request: HttpServletRequest,
        e: ValidArgumentException,
    ): String {
        val message = messageSource.getMessage(e.message, e.args, request.locale)
        logger.error("${RequestIdContextHolder.getRequestId()} ${e.responseStatus} $message")
        return "${RequestIdContextHolder.getRequestId()} ${e.responseStatus} $message"
    }

    @ExceptionHandler(NestedRuntimeException::class)
    fun handleNestedRuntimeException(
        request: HttpServletRequest,
        e: NestedRuntimeException,
    ): String {
        val message = when (e.rootCause) {
            is ValidArgumentException -> handleValidArgumentException(request, e.rootCause as ValidArgumentException)
            else -> e.rootCause?.message ?: e.message
        }
        logger.error("${RequestIdContextHolder.getRequestId()} $message")
        return "${RequestIdContextHolder.getRequestId()} $message"
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