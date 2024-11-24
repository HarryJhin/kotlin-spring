package io.github.harryjhin.filter

import io.github.harryjhin.logId
import io.github.harryjhin.initLogId
import io.github.harryjhin.removeLogId
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class RequestIdFilter : OncePerRequestFilter() {

    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        try {
            request.initLogId()
            log.info("${request.logId} Request received...")
            filterChain.doFilter(request, response)
        } finally {
            request.removeLogId()
        }
    }
}
