package io.github.harryjhin.domain.security.filter

import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

object SecurityCorsFilter : CorsFilter(UrlBasedCorsConfigurationSource().apply {
    registerCorsConfiguration(
        "/**",
        CorsConfiguration().apply {
            allowCredentials = true
            addAllowedOrigin("*")
            addAllowedHeader("*")
            addAllowedMethod("*")
        }
    )
})