package io.github.harryjhin.bootstrap.member

import io.github.harryjhin.common.member.Member
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort

interface GetAllMember {

    operator fun invoke(): List<Member>

    operator fun invoke(sort: Sort): List<Member>

    operator fun invoke(pageable: Pageable): Page<Member>
}