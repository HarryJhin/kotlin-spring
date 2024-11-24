package io.github.harryjhin.filter

import io.github.harryjhin.context.RequestIdContextHolder
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class RequestIdFilter : OncePerRequestFilter() {

    private val log: Logger = LoggerFactory.getLogger(RequestIdFilter::class.java)

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        try {
            RequestIdContextHolder.setRequestId("[ID-${request.hashCode()}]")
            log.debug("${RequestIdContextHolder.getRequestId()} Request Id Context Set")
            filterChain.doFilter(request, response)
        } finally {
            log.debug("${RequestIdContextHolder.getRequestId()} Request Id Context Clear")
            RequestIdContextHolder.clear()
        }
    }
}
