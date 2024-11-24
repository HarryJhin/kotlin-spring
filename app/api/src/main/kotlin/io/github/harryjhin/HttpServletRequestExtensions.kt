package io.github.harryjhin

import jakarta.servlet.http.HttpServletRequest

private const val LOG_ID = "requestId"

val HttpServletRequest.logId: String
    get() = "[${getAttribute(LOG_ID)}]"

fun HttpServletRequest.initLogId() {
    setAttribute(LOG_ID, "ID-${hashCode()}")
}

fun HttpServletRequest.removeLogId() {
    removeAttribute(LOG_ID)
}
