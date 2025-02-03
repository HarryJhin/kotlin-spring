package io.github.harryjhin.domain.security.user

import io.github.harryjhin.bootstrap.member.ReadMember
import io.github.harryjhin.common.member.toUsername
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class PrincipalDetailsService(
    private val readMember: ReadMember,
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val member = readMember(username.toUsername()) ?: throw UsernameNotFoundException(username)
        return PrincipalDetails(member)
    }
}