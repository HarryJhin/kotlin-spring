package io.github.harryjhin.domain.member.repository

import io.github.harryjhin.entity.password.PasswordEntity
import io.github.harryjhin.model.member.MemberId
import io.github.harryjhin.model.password.EncodedPassword
import io.github.harryjhin.model.password.PasswordStrength
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import kotlin.test.Test
import kotlin.test.assertEquals

@DataJpaTest
class PasswordRepositoryTest @Autowired constructor(
    private val passwordRepository: PasswordRepository,
) {

    private val strength = 10
    private val passwordEncoder = BCryptPasswordEncoder(strength)

    @Test
    fun `비밀번호 생성`() {
        // given
        val password = PasswordEntity {
            this.memberId = MemberId(1)
            this.strength = PasswordStrength(this@PasswordRepositoryTest.strength)
            this.encodedPassword = EncodedPassword(passwordEncoder.encode("password"))
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
            this.strength = PasswordStrength(this@PasswordRepositoryTest.strength)
            this.encodedPassword = EncodedPassword(passwordEncoder.encode("password"))
        }.run(passwordRepository::save)

        // when
        val entity = passwordRepository.findByMemberId(password.memberId)

        // then
        assertEquals(
            expected = password.password,
            actual = entity?.password,
        )
    }
}
