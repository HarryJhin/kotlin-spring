package io.github.harryjhin.model.member.password

import io.github.harryjhin.model.member.ModelMemberConfiguration.Companion.PASSWORD_ENCODER
import kotlin.test.assertEquals
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PasswordTest {

    @Test
    fun `raw password should be encoded`() {
        // Given
        val password = "password123!"
        val rawPassword = RawPassword(password)
        val encodedPassword = EncodedPassword(PASSWORD_ENCODER.encode(password))

        assertTrue(
            actual = PASSWORD_ENCODER.matches(rawPassword.value, encodedPassword.value)
        )

        assertTrue(
            actual = rawPassword.matches(encodedPassword)
        )

        assertTrue(
            actual = encodedPassword.matches(rawPassword)
        )

        assertTrue(
            actual = rawPassword.encode().matches(rawPassword)
        )

        assertFalse(
            actual = encodedPassword.matches(RawPassword("wrong123!"))
        )
    }

    @Test
    fun `인코딩되지 않은 EncodedPassword 생성 시 예외 발생`() {
        // Given

        // When
        val exception = assertFailsWith<IllegalArgumentException> {
            EncodedPassword("rawPassword")
        }

        // Then
        assertEquals(
            expected = "인코딩된 비밀번호는 BCrypt와 유사해야 합니다.",
            actual = exception.message
        )
    }
}