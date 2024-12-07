package io.github.harryjhin.domain.member

import io.github.harryjhin.domain.member.property.PasswordProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(PasswordProperties::class)
class DomainMemberConfiguration {

    @Bean
    fun passwordEncoder(
        properties: PasswordProperties,
    ): PasswordEncoder {
        return BCryptPasswordEncoder(properties.strength.value)
    }
}
