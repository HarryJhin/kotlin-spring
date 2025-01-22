package io.github.harryjhin.common.exception

class ValidArgumentException(
    override val message: String,
    override val responseMessage: String,
    vararg val args: Any
) : ResponseException() {
    override val responseStatus: Int = 400

    constructor(message: String) : this(message, message)

    constructor(message: String, vararg args: Any) : this(message, message, *args)
}