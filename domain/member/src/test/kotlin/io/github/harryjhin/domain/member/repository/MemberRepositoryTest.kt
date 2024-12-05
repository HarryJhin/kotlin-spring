package io.github.harryjhin.domain.member.repository

import io.github.harryjhin.domain.member.projection.UsernameAndEmailProjection
import io.github.harryjhin.entity.member.MemberEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import kotlin.test.Test
import kotlin.test.assertEquals

@DataJpaTest
class MemberRepositoryTest @Autowired constructor(
    private val memberRepository: MemberRepository,
) {

    @Test
    fun `회원 저장`() {
        // given
        val member = MemberEntity()

        // when
        memberRepository.save(member)

        val actual: MemberEntity = memberRepository.findById(member.id).get()

        // then
        assertEquals(
            expected = member,
            actual = actual,
        )
    }

    @Test
    fun `email로 회원 검색`() {
        // given
        val given = MemberEntity()

        // when
        memberRepository.save(given)

        val actual: UsernameAndEmailProjection = memberRepository.findByEmail(given.email)

        // then
        assertEquals(
            expected = given.email,
            actual = actual.email,
        )
    }
}
