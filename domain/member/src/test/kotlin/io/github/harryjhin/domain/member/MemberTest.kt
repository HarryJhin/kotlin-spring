package io.github.harryjhin.domain.member

import io.github.harryjhin.domain.member.port.GetAllMember
import io.github.harryjhin.domain.member.port.GetMember
import io.github.harryjhin.domain.member.port.SaveMember
import io.github.harryjhin.domain.member.projection.CompositeMemberDto
import io.github.harryjhin.model.email.Email
import io.github.harryjhin.model.name.toName
import io.github.harryjhin.model.member.Username
import io.github.harryjhin.model.member.RawPassword
import jakarta.transaction.Transactional
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
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

    private lateinit var member: CompositeMemberDto

    @BeforeEach
    fun setUp() {
        val username = Username("tester")
        val rawPassword = RawPassword("password")
        val email = Email("tester@gmail.com")

        member = saveMember {
            this.name = "테스터".toName()
            this.email = email
            this.username = username
            this.rawPassword = rawPassword
        }
    }

    @Test
    fun `member_id 회원 조회`() {
        // given

        // when
        val member = getMember(member.memberId)

        // then
        assertNotNull(member)
    }

    @Test
    fun `username 회원 조회`() {
        // given

        // when
        val member = getMember(member.username)

        // then
        assertNotNull(member)
    }

    @Test
    fun `회원 전체 조회`() {
        // given

        // when
        val entities = getAllMember()

        // then
        assertEquals(
            expected = 1,
            actual = entities.size,
        )
    }
}