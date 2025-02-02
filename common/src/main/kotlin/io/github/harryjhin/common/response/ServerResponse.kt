package io.github.harryjhin.common.response

class ServerResponse<T>(
    val result: ServerResult,
    val payload: T,
) {

    override fun toString(): String {
        return """
            {
                "result": $result,
                "payload": $payload
            }
        """.trimIndent()
    }

    class Builder<T> internal constructor(
        var result: ServerResult? = null,
        var payload: T? = null,
    ) {
        internal fun build(): ServerResponse<T> {
            requireNotNull(payload) { "serverResponse.payload must not be null" }
            return ServerResponse(result ?: ServerResult.ok(), payload!!)
        }
    }
}

fun <T> ServerResponse(
    builder: ServerResponse.Builder<T> = ServerResponse.Builder(),
    buildToAction: ServerResponse.Builder<T>.() -> Unit,
): ServerResponse<T> = builder.apply(buildToAction).build()