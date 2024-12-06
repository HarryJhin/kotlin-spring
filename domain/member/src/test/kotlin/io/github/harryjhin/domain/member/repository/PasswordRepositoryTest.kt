package io.github.harryjhin.domain.member.repository

import io.github.harryjhin.entity.member.PasswordEntity
import io.github.harryjhin.model.member.MemberId
import io.github.harryjhin.model.member.password.RawPassword
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import kotlin.test.Test
import kotlin.test.assertEquals

@DataJpaTest
class PasswordRepositoryTest @Autowired constructor(
    private val passwordRepository: PasswordRepository,
) {

    @Test
    fun `비밀번호 생성`() {
        // given
        val password = PasswordEntity {
            this.memberId = MemberId(1)
            this.rawPassword = RawPassword("password")
        }

        // when
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
        val password = PasswordEntity {
            this.memberId = MemberId(1)
            this.rawPassword = RawPassword("password")
        }

        // when
        passwordRepository.save(password)
        val entity = passwordRepository.getFirstByMemberIdOrderByIdDesc(password.memberId)

        // then
        assertEquals(
            expected = password.password,
            actual = entity.password,
        )
    }
}
