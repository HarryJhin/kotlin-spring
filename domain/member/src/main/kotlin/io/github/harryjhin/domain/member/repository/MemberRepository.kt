package io.github.harryjhin.domain.member.repository

import io.github.harryjhin.domain.member.projection.UsernameAndEmailProjection
import io.github.harryjhin.entity.member.MemberEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<MemberEntity, Long> {

    fun findByEmail(email: String): UsernameAndEmailProjection
}
