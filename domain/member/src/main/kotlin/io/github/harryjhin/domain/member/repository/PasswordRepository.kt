package io.github.harryjhin.domain.member.repository

import io.github.harryjhin.domain.member.projection.PasswordProjection
import io.github.harryjhin.entity.member.PasswordEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PasswordRepository : JpaRepository<PasswordEntity, Long> {

    fun getFirstByMemberIdOrderByIdDesc(memberId: Long): PasswordProjection
}
