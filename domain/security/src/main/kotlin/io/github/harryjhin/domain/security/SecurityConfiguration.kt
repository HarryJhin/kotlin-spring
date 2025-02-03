package io.github.harryjhin.domain.security

import io.github.harryjhin.bootstrap.member.ReadMember
import io.github.harryjhin.domain.security.filter.JwtAuthenticationFilter
import io.github.harryjhin.domain.security.filter.JwtAuthorizationFilter
import io.github.harryjhin.domain.security.filter.SecurityCorsFilter
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(SecurityProperties::class)
class SecurityConfiguration(
    private val readMember: ReadMember,
    private val securityProperties: SecurityProperties,
) {

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer { webSecurity ->
            webSecurity.ignoring()
                // ignore all URLs that start with / resources/
                .requestMatchers("/resources/**")
        }
    }

    @Bean
    fun securityFilterChain(
        http: HttpSecurity
    ): SecurityFilterChain {
        return http
            .csrf { csrf -> csrf.disable() }
            .sessionManagement { sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .formLogin { formLogin -> formLogin.disable() }
            .httpBasic { httpBasic -> httpBasic.disable() }
            .with(
                SecurityConfigurerDsl(
                    readMember = readMember,
                    securityProperties = securityProperties,
                ),
                Customizer.withDefaults()
            )
            .authorizeHttpRequests { authorizeRequests ->
                authorizeRequests
                    .requestMatchers(*securityProperties.uncheckPaths).permitAll()
                    .anyRequest().authenticated()
            }
            .build()
    }

    class SecurityConfigurerDsl(
        private val readMember: ReadMember,
        private val securityProperties: SecurityProperties,
    ) : AbstractHttpConfigurer<SecurityConfigurerDsl, HttpSecurity>() {

        override fun configure(http: HttpSecurity) {
            val authenticationManager: AuthenticationManager = http.getSharedObject(AuthenticationManager::class.java)

            http.addFilter(SecurityCorsFilter)
                .addFilter(
                    JwtAuthenticationFilter(
                        securityProperties = securityProperties,
                        authenticationManager = authenticationManager,
                    )
                )
                .addFilter(
                    JwtAuthorizationFilter(
                        readMember = readMember,
                        authenticationManager = authenticationManager,
                    )
                )
        }
    }
}