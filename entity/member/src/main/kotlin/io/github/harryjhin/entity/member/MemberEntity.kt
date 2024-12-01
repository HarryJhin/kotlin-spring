package io.github.harryjhin.entity.member

import io.github.harryjhin.entity.core.ModifiableBaseEntity
import io.github.harryjhin.model.core.email.Email
import io.github.harryjhin.model.member.username.Username
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Index
import jakarta.persistence.Table

@Entity
@Table(
    name = "MEMBER",
    indexes = [
        Index(name = "IDX_MEMBER_1", columnList = "USER_NAME", unique = true),
        Index(name = "IDX_MEMBER_2", columnList = "EMAIL"),
    ],
)
class MemberEntity internal constructor(
    builder: MemberEntityBuilder,
) : ModifiableBaseEntity<Long>() {

    @Column(name = "USER_NAME", nullable = false)
    var username: String = builder.username!!.value
        protected set

    @Column(name = "EMAIL", nullable = false)
    var email: String = builder.email!!.value
        protected set
}

class MemberEntityBuilder internal constructor(
    var username: Username? = null,
    var email: Email? = null,
) {

    internal fun build(): MemberEntity {
        requireNotNull(username) { "`username` 프로퍼티는 필수입니다." }
        requireNotNull(email) { "`email` 프로퍼티는 필수입니다." }
        return MemberEntity(this)
    }
}

/**
 * [MemberEntity]를 생성합니다.
 *
 * ```kotlin
 * val member = MemberEntity {
 *     this.name = Name("테스터")
 *     this.email = Email("tester@gmail.com")
 * }
 * ```
 *
 * @param buildToAction [MemberEntityBuilder]를 빌드합니다.
 * @return [MemberEntity]
 */
fun MemberEntity(
    builder: MemberEntityBuilder = MemberEntityBuilder(),
    buildToAction: MemberEntityBuilder.() -> Unit = {
        this.username = Username("tester")
        this.email = Email("tester@gmail.com")
    },
): MemberEntity = builder.apply(buildToAction).build()
