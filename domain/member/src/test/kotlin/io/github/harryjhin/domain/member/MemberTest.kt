package io.github.harryjhin.domain.member

import io.github.harryjhin.bootstrap.member.ReadAllMember
import io.github.harryjhin.bootstrap.member.ReadMember
import io.github.harryjhin.bootstrap.member.CreateMember
import io.github.harryjhin.common.email.toEmail
import io.github.harryjhin.common.member.MemberCompat
import io.github.harryjhin.common.member.toRawPassword
import io.github.harryjhin.common.name.toName
import jakarta.transaction.Transactional
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import org.junit.jupiter.api.BeforeAll
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort

@Transactional
@SpringBootTest
class MemberTest @Autowired constructor(
    private val readMember: ReadMember,
    private val readAllMember: ReadAllMember,
) {

    @Test
    fun `회원 조회 #member_id`() {
        // given

        // when
        val member: MemberCompat? = readMember(memberId = members.first().memberId)
            ?.also(::println)

        // then
        assertNotNull(member)
    }

    @Test
    fun `회원 조회 #username`() {
        // given

        // when
        val member: MemberCompat? = readMember(username = members.first().username)
            ?.also(::println)

        // then
        assertNotNull(member)
    }

    @Test
    fun `회원 전체 조회`() {
        // given

        // when
        val results: List<MemberCompat> = readAllMember()
            .onEach(::println)

        // then
        assertEquals(
            expected = members.size,
            actual = results.size,
        )
    }

    @Test
    fun `회원 전체 조회 #sort`() {
        // given

        // when
        val results: List<MemberCompat> = readAllMember(sort = Sort.by(Sort.Order.desc("id")))
            .onEach(::println)

        // then
        assertEquals(
            expected = members.size,
            actual = results.size,
        )

        assertEquals(
            expected = members.last().memberId,
            actual = results.first().memberId,
        )
    }

    @Test
    fun `회원 전체 조회 #pageable`() {
        // given
        val pageable = PageRequest.of(1, 2, Sort.by(Sort.Order.desc("email")))

        // when
        val page: Page<MemberCompat> = readAllMember(pageable = pageable)
            .onEach(::println)

        // then
        assertEquals(
            expected = members.size,
            actual = page.totalElements.toInt(),
        )

        assertEquals(
            expected = pageable.pageSize,
            actual = page.size,
        )
    }

    companion object {
        private val members: MutableList<MemberCompat> = mutableListOf()

        @BeforeAll
        @JvmStatic
        fun beforeAll(
            @Autowired createMember: CreateMember,
        ) {
            createMember {
                this.name = "테스터".toName()
                this.email = "tester1@gmail.com".toEmail()
                this.rawPassword = "password".toRawPassword()
            }.run(members::add)

            createMember {
                this.name = "테스터".toName()
                this.email = "tester2@gmail.com".toEmail()
                this.rawPassword = "password".toRawPassword()
            }.run(members::add)

            createMember {
                this.name = "테스터".toName()
                this.email = "tester3@gmail.com".toEmail()
                this.rawPassword = "password".toRawPassword()
            }.run(members::add)

            createMember {
                this.name = "테스터".toName()
                this.email = "tester4@gmail.com".toEmail()
                this.rawPassword = "password".toRawPassword()
            }.run(members::add)

            createMember {
                this.name = "테스터".toName()
                this.email = "tester5@gmail.com".toEmail()
                this.rawPassword = "password".toRawPassword()
            }.run(members::add)
        }
    }
}