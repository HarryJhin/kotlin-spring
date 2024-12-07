package io.github.harryjhin.filter

import io.github.harryjhin.context.RequestIdContextHolder
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class RequestIdFilter : OncePerRequestFilter() {

    private val log: Logger = LoggerFactory.getLogger(RequestIdFilter::class.java)

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        try {
            MDC.put("requestId", "[ID-${request.hashCode()}]")
            RequestIdContextHolder.setRequestId("[ID-${request.hashCode()}]")
            log.debug("${RequestIdContextHolder.getRequestId()} Request Id Context Set")
            filterChain.doFilter(request, response)
        } finally {
            log.debug("${RequestIdContextHolder.getRequestId()} Request Id Context Clear")
            MDC.clear()
            RequestIdContextHolder.clear()
        }
    }
}
