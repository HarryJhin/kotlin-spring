package io.github.harryjhin.domain.member

import io.github.harryjhin.domain.member.port.GetAllMember
import io.github.harryjhin.domain.member.port.GetMember
import io.github.harryjhin.domain.member.port.SaveMember
import io.github.harryjhin.domain.member.projection.Member
import io.github.harryjhin.model.email.toEmail
import io.github.harryjhin.model.member.toRawPassword
import io.github.harryjhin.model.name.toName
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
    private val saveMember: SaveMember,
    private val getMember: GetMember,
    private val getAllMember: GetAllMember,
) {

    private lateinit var member: Member

    @BeforeEach
    fun setUp() {
        member = saveMember {
            this.name = "테스터".toName()
            this.email = "tester@gmail.com".toEmail()
            this.rawPassword =  "password".toRawPassword()
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
        val member = getMember(username = member.username)

        // then
        assertNotNull(member)
    }

    @Test
    fun `email 회원 조회`() {
        // given

        // when
        val member = getMember(email = member.email)

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