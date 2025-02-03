package io.github.harryjhin.domain.security

import io.jsonwebtoken.Jwts
import jakarta.validation.constraints.NotBlank
import java.time.Duration
import javax.crypto.SecretKey
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.DefaultValue
import org.springframework.validation.annotation.Validated

@Validated
@ConfigurationProperties(prefix = "security")
class SecurityProperties(
    @DefaultValue("spring security") @field:NotBlank
    val issuer: String,
    @DefaultValue("PT30M")
    val accessExpiration: Duration,
    @DefaultValue("P90D")
    val refreshExpiration: Duration,
    val uncheckPaths: Array<String>,
) {
    companion object {
        const val HEADER = "Authorization"
        const val TOKEN_PREFIX = "Bearer "
        val SECRET_KEY: SecretKey = Jwts.SIG.HS256.key().build()
    }
}