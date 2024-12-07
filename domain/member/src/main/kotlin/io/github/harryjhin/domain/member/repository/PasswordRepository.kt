package io.github.harryjhin.domain.member.repository

import io.github.harryjhin.entity.password.PasswordEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PasswordRepository : JpaRepository<PasswordEntity, Long> {

    fun findByMemberId(memberId: Long): PasswordEntity?
}
