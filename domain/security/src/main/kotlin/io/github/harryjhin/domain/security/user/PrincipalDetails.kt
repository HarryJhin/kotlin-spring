package io.github.harryjhin.domain.security.user

import io.github.harryjhin.common.member.MemberCompat
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class PrincipalDetails(private val member: MemberCompat) : UserDetails, MemberCompat by member {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        TODO("Not yet implemented")
    }

    override fun getPassword(): String {
        return member.password.value
    }

    override fun getUsername(): String {
        return member.username.value
    }
}