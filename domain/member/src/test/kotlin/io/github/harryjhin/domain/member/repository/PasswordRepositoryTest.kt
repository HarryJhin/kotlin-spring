package io.github.harryjhin.domain.member.repository

import io.github.harryjhin.entity.member.MemberEntity
import io.github.harryjhin.entity.member.PasswordEntity
import io.github.harryjhin.model.member.password.RawPassword
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import kotlin.test.Test
import kotlin.test.assertEquals

@DataJpaTest
class PasswordRepositoryTest @Autowired constructor(
    private val memberRepository: MemberRepository,
    private val passwordRepository: PasswordRepository,
) {

    @Test
    fun `비밀번호 생성`() {
        // given
        val member = MemberEntity()
        val password = PasswordEntity {
            this.member = member
            this.rawPassword = RawPassword("password")
        }

        // when
        memberRepository.save(member)
        val entity = passwordRepository.save(password)

        // then
        assertEquals(
            expected = password.password,
            actual = entity.password,
        )
    }

    @Test
    fun `비밀번호 찾기`() {
        // given
        val member = MemberEntity()
        val password = PasswordEntity {
            this.member = member
            this.rawPassword = RawPassword("password")
        }

        // when
        memberRepository.save(member)
        passwordRepository.save(password)
        val entity = passwordRepository.getTopByMember_IdOrderByIdDesc(member.id)

        // then
        assertEquals(
            expected = password.password,
            actual = entity.password,
        )
    }
}
