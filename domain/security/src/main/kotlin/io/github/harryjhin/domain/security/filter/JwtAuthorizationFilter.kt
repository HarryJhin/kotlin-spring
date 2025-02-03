package io.github.harryjhin.domain.security.filter

import io.github.harryjhin.bootstrap.member.ReadMember
import io.github.harryjhin.common.member.MemberCompat
import io.github.harryjhin.common.member.Username
import io.github.harryjhin.common.member.toUsername
import io.github.harryjhin.domain.security.SecurityProperties
import io.github.harryjhin.domain.security.user.PrincipalDetails
import io.jsonwebtoken.Jwts
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

class JwtAuthorizationFilter(
    private val readMember: ReadMember,
    authenticationManager: AuthenticationManager,
) : BasicAuthenticationFilter(authenticationManager) {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain
    ) {
        var jws: String? = request.getHeader(SecurityProperties.HEADER)

        if (jws == null || !jws.startsWith(SecurityProperties.TOKEN_PREFIX)) {
            chain.doFilter(request, response)
            return
        }

        jws = jws.replace(SecurityProperties.TOKEN_PREFIX, "")

        // JWT 검증이 곧 인증이므로, `AuthenticationManager`를 사용하지 않는다.
        val username: Username? = Jwts.parser()
            .verifyWith(SecurityProperties.SECRET_KEY)
            .build()
            .parseSignedClaims(jws)
            .payload
            .subject
            ?.toUsername()

        // Spring Security 인가를 위해 `Authentication` 객체를 생성하고 세션(`SecurityContextHolder`)에 저장한다.
        if (username != null) {
            val member: MemberCompat? = readMember(username = username)

            if (member == null) {
                chain.doFilter(request, response)
                return
            }

            val principalDetails = PrincipalDetails(member = member)
            val authentication = UsernamePasswordAuthenticationToken(
                principalDetails,
                null, // 비밀번호는 인가에서 필요하지 않다.
                principalDetails.authorities,
            )

            SecurityContextHolder.getContext().authentication = authentication
        }

        chain.doFilter(request, response)
    }
}