package io.github.harryjhin.model.member

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration(proxyBeanMethods = false)
class ModelMemberConfiguration {

    companion object {
        val PASSWORD_ENCODER: PasswordEncoder = BCryptPasswordEncoder()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return PASSWORD_ENCODER
    }
}
