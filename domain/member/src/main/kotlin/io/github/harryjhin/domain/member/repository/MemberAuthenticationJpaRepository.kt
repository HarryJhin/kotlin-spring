package io.github.harryjhin.domain.member.repository

import io.github.harryjhin.entity.member.MemberAuthenticationEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Transactional

@Transactional
interface MemberAuthenticationJpaRepository : JpaRepository<MemberAuthenticationEntity, Long>