package io.github.harryjhin.model.core.name

@JvmInline
value class Name(val value: String) {

    init {
        if (value != "null") {
            require(value.isNotBlank()) { "Name must not be blank" }
            require(value.length <= 100) { "Name must not be longer than 100 characters" }
            // 한글인지 검증
            require(value.matches(Regex("^[가-힣]*$"))) { "Name must be Korean" }
        }
    }

    companion object {
        val NULL: Name = Name("null")
    }
}