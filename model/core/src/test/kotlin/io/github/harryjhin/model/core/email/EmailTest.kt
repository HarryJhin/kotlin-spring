package io.github.harryjhin.model.core.email

import kotlin.test.Test
import kotlin.test.assertFailsWith

class EmailTest {

    @Test
    fun `길이 제한`() {
        // given
        val given = "a".repeat(256) + "@tester.com"

        // when
        assertFailsWith<IllegalArgumentException> {
            Email(given)
        }
    }

    @Test
    fun `잘못된 도메인`() {
        // given
        val given = "example@example*com"

        // when
        assertFailsWith<IllegalArgumentException> {
            Email(given)
        }
    }
}
