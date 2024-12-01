package io.github.harryjhin.model.member.password

import io.github.harryjhin.model.member.ModelMemberConfiguration.Companion.PASSWORD_ENCODER
import java.util.regex.Pattern

@JvmInline
value class EncodedPassword(val value: String) {

    init {
        require(value.isNotBlank()) { "인코딩된 비밀번호는 공백일 수 없습니다." }
        require(BCRYPT_PATTERN.matcher(value).matches()) { "인코딩된 비밀번호는 BCrypt와 유사해야 합니다." }
    }

    fun matches(rawPassword: RawPassword): Boolean {
        return PASSWORD_ENCODER.matches(rawPassword.value, value)
    }

    override fun toString(): String {
        return "*****"
    }

    private companion object {
        val BCRYPT_PATTERN: Pattern = Pattern.compile("\\A\\$2([ayb])?\\$(\\d\\d)\\$[./0-9A-Za-z]{53}")
    }
}
