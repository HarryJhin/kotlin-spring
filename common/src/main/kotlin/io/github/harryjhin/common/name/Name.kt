package io.github.harryjhin.common.name

@JvmInline
value class Name(val value: String) {

    init {
        require(value.isNotBlank()) { "Name must not be blank" }
        require(value.length <= 100) { "Name must not be longer than 100 characters" }
        // 한글인지 검증
        require(value.matches(Regex("^[가-힣]*$"))) { "Name must be Korean" }
    }

    override fun toString(): String {
        return value
    }
}

fun String.toName(): Name = Name(this)
