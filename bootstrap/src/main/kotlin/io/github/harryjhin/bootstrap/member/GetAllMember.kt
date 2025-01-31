package io.github.harryjhin.bootstrap.member

import io.github.harryjhin.common.member.MemberCompat
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort

interface GetAllMember {

    operator fun invoke(): List<MemberCompat>

    operator fun invoke(sort: Sort): List<MemberCompat>

    operator fun invoke(pageable: Pageable): Page<MemberCompat>
}