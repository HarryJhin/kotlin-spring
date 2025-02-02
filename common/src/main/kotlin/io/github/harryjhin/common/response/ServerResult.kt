package io.github.harryjhin.common.response

class ServerResult internal constructor(
    val code: Int,
    val message: String,
) {

    fun toServerResponse() = ServerResponse<Unit> {
        result = this@ServerResult
        payload = Unit
    }

    override fun toString(): String {
        return """
            {
                "code": $code,
                "message": "$message"
            }
        """.trimIndent()
    }

    class Builder internal constructor(
        var code: Int? = null,
        var message: String? = null,
    ) {
        internal fun build(): ServerResult {
            requireNotNull(code) { "serverResult.code must not be null" }
            requireNotNull(message) { "serverResult.message must not be null" }
            return ServerResult(code!!, message!!)
        }
    }

    companion object {

        fun ok(
            message: String = "OK",
        ): ServerResult = ServerResult(code = 200, message = message)

        fun created(
            message: String = "Created",
        ): ServerResult = ServerResult(code = 201, message = message)

        fun noContent(
            message: String = "No Content",
        ): ServerResult = ServerResult(code = 204, message = message)

        fun alreadyReported(
            message: String = "Already Reported",
        ): ServerResult = ServerResult(code = 208, message = message)

        fun imUsed(
            message: String = "IM Used",
        ): ServerResult = ServerResult(code = 226, message = message)

        fun badRequest(
            message: String = "Bad Request",
        ): ServerResult = ServerResult(code = 400, message = message)

        fun unauthorized(
            message: String = "Unauthorized",
        ): ServerResult = ServerResult(code = 401, message = message)

        fun forbidden(
            message: String = "Forbidden",
        ): ServerResult = ServerResult(code = 403, message = message)

        fun notFound(
            message: String = "Not Found",
        ): ServerResult = ServerResult(code = 404, message = message)

        fun methodNotAllowed(
            message: String = "Method Not Allowed",
        ): ServerResult = ServerResult(code = 405, message = message)

        fun conflict(
            message: String = "Conflict",
        ): ServerResult = ServerResult(code = 409, message = message)

        fun gone(
            message: String = "Gone",
        ): ServerResult = ServerResult(code = 410, message = message)

        fun tooEarly(
            message: String = "Too Early",
        ): ServerResult = ServerResult(code = 425, message = message)

        fun internalServerError(
            message: String = "Internal Server Error",
        ): ServerResult = ServerResult(code = 500, message = message)
    }
}

fun ServerResult(
    builder: ServerResult.Builder = ServerResult.Builder(),
    buildToAction: ServerResult.Builder.() -> Unit,
): ServerResult = builder.apply(buildToAction).build()