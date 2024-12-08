package io.github.harryjhin.domain.member.function

import io.github.harryjhin.domain.member.model.Member
import io.github.harryjhin.model.core.email.Email
import io.github.harryjhin.model.member.Username
import io.github.harryjhin.model.password.RawPassword
import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test
import kotlin.test.assertEquals

@Transactional
@SpringBootTest
class MemberTest @Autowired constructor(
    private val getMember: GetMemberUseCase,
    private val saveMember: SaveMemberUseCase,
    private val getAllMember: GetAllMemberUseCase,
    private val entityManager: EntityManager,
    private val getPassword: GetPasswordUseCase,
) {

    private lateinit var member: Member

    @BeforeEach
    fun setUp() {
        val username = Username("tester")
        val rawPassword = RawPassword("password")
        val email = Email("tester@gmail.com")

        member = saveMember(
            username = username,
            rawPassword = rawPassword,
            email = email,
        )

        entityManager.flush()
        entityManager.clear()
    }

    @Test
    fun `회원 조회`() {
        // given

        // when
        val entity = getMember(id = member.memberId).also(::println)

        // then
        assertEquals(
            expected = member.username,
            actual = entity.username,
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

    @Test
    fun `비밀번호 조회`() {
        // given

        // when
        val password = getPassword(member.memberId).also(::println)

        // then
        assertEquals(
            expected = member.memberId,
            actual = password?.memberId,
        )
    }
}
