package io.github.harryjhin.domain.member

import io.github.harryjhin.domain.member.dto.MemberDto
import io.github.harryjhin.domain.member.port.GetAllMember
import io.github.harryjhin.domain.member.port.GetMember
import io.github.harryjhin.domain.member.port.SaveMember
import io.github.harryjhin.model.core.email.Email
import io.github.harryjhin.model.member.Username
import io.github.harryjhin.model.password.RawPassword
import jakarta.transaction.Transactional
import kotlin.test.Test
import kotlin.test.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@Transactional
@SpringBootTest
class MemberTest @Autowired constructor(
    private val getMember: GetMember,
    private val saveMember: SaveMember,
    private val getAllMember: GetAllMember,
) {

    private lateinit var member: MemberDto

    @BeforeEach
    fun setUp() {
        val username = Username("tester")
        val rawPassword = RawPassword("password")
        val email = Email("tester@gmail.com")

        member = saveMember {
            this.username = username
            this.email = email
            this.rawPassword = rawPassword
        }
    }

    @Test
    fun `회원 조회`() {
        // given

        // when
        val member = getMember(member.memberId).also(::println)

        // then
        assertEquals(
            expected = this.member,
            actual = member,
        )
    }

    @Test
    fun `회원 전체 조회`() {
        // given

        // when
        val entities = getAllMember().also(::println)

        // then
        assertEquals(
            expected = 1,
            actual = entities.size,
        )
    }
}