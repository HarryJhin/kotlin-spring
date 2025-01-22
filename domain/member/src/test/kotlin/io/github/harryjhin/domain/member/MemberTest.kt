package io.github.harryjhin.domain.member

import io.github.harryjhin.bootstrap.member.GetAllMember
import io.github.harryjhin.bootstrap.member.GetMember
import io.github.harryjhin.bootstrap.member.SaveMember
import io.github.harryjhin.common.email.toEmail
import io.github.harryjhin.common.member.Member
import io.github.harryjhin.common.member.toRawPassword
import io.github.harryjhin.common.name.toName
import jakarta.transaction.Transactional
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort

@Transactional
@SpringBootTest
class MemberTest @Autowired constructor(
    private val saveMember: SaveMember,
    private val getMember: GetMember,
    private val getAllMember: GetAllMember,
) {

    private val members: MutableList<Member> = mutableListOf()

    @BeforeEach
    fun setUp() {
        members.add(
            saveMember {
                this.name = "테스터".toName()
                this.email = "tester1@gmail.com".toEmail()
                this.rawPassword = "password".toRawPassword()
            }
        )

        members.add(
            saveMember {
                this.name = "테스터".toName()
                this.email = "tester2@gmail.com".toEmail()
                this.rawPassword = "password".toRawPassword()
            }
        )

        members.add(
            saveMember {
                this.name = "테스터".toName()
                this.email = "tester3@gmail.com".toEmail()
                this.rawPassword = "password".toRawPassword()
            }
        )

        members.add(
            saveMember {
                this.name = "테스터".toName()
                this.email = "tester4@gmail.com".toEmail()
                this.rawPassword = "password".toRawPassword()
            }
        )

        members.add(
            saveMember {
                this.name = "테스터".toName()
                this.email = "tester5@gmail.com".toEmail()
                this.rawPassword = "password".toRawPassword()
            }
        )
    }

    @Test
    fun `회원 조회 #member_id`() {
        // given

        // when
        val member = getMember(memberId = members.first().memberId)

        // then
        assertNotNull(member)
    }

    @Test
    fun `회원 조회 #username`() {
        // given

        // when
        val member = getMember(username = members.first().username)

        // then
        assertNotNull(member)
    }

    @Test
    fun `회원 전체 조회`() {
        // given

        // when
        val results = getAllMember()

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
        val results = getAllMember(sort = Sort.by(Sort.Order.desc("id")))
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
        val page: Page<Member> = getAllMember(pageable = pageable)
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
}