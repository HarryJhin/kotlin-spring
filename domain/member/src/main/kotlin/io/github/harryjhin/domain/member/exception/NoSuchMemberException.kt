package io.github.harryjhin.domain.member.exception

class NoSuchMemberException(
    override val message: String,
) : MemberException() {

    override val responseStatus: Int = 404
    override val responseMessage: String = "회원 정보를 찾을 수 없습니다."
}
