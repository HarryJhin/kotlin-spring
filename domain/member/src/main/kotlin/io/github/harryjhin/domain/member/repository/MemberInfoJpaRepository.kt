package io.github.harryjhin.domain.member.repository

import io.github.harryjhin.entity.member.MemberEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Transactional

@Transactional
interface MemberInfoJpaRepository : JpaRepository<MemberEntity, Long>