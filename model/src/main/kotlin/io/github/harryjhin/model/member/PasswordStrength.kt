package io.github.harryjhin.model.member

@JvmInline
value class PasswordStrength(val value: Int) {

    init {
        require(value in 4..31) { "비밀번호 강도는 4 이상 31 이하로 구성되어야 합니다." }
    }

    override fun toString(): String {
        return "*****"
    }
}

fun Int.toPasswordStrength(): PasswordStrength = PasswordStrength(this)
