package io.github.harryjhin.domain.security.filter

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.harryjhin.domain.security.SecurityProperties
import io.github.harryjhin.domain.security.user.PrincipalDetails
import io.jsonwebtoken.Jwts
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import java.util.Date
import javax.crypto.SecretKey
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class JwtAuthenticationFilter(
    private val securityProperties: SecurityProperties,
    private val authenticationManager: AuthenticationManager,
) : UsernamePasswordAuthenticationFilter() {

    private val objectMapper = ObjectMapper()

    override fun attemptAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
    ): Authentication {
        val loginRequest: LoginRequest = objectMapper.readValue(request.inputStream, LoginRequest::class.java)

        val token = UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password)

        // authenticate() 함수가 호출되면 인증 프로바이더가 UserDetailsService#loadUserByUsername() 메서드를 호출한다.
        // 반환된 UserDetails 인스턴스를 토큰의 두번째 인자(`credential`)와 UserDetails#password 프로퍼티를 비교한다.
        // 비밀번호가 일치하면 인증이 성공하고, 인증 객체가 생성된다.
        val authentication: Authentication = authenticationManager.authenticate(token)

        return authentication
    }

    override fun successfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain,
        authResult: Authentication,
    ) {
        val details: PrincipalDetails = authResult.principal as PrincipalDetails
        val now = Date()
        val accessExpiration: Long = now.toInstant().plus(securityProperties.accessExpiration).epochSecond * 1_000L

        val jws: String = Jwts.builder()
            .subject(details.getUsername())
            .signWith(SecurityProperties.SECRET_KEY)
            .issuer(securityProperties.issuer)
            .issuedAt(now)
            .expiration(Date(accessExpiration))
            .compact()

        response.addHeader(SecurityProperties.HEADER, SecurityProperties.TOKEN_PREFIX + jws)
    }

    data class LoginRequest(
        val username: String,
        val password: String,
    )
}