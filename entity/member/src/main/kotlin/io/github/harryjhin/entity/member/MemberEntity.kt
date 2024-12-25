package io.github.harryjhin.entity.member

import io.github.harryjhin.entity.core.ModifiableBaseEntity
import io.github.harryjhin.entity.core.listener.LoggingListener
import io.github.harryjhin.model.email.Email
import io.github.harryjhin.model.name.Name
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.Index
import jakarta.persistence.Table

@Entity
@Table(
    name = "MEMBER",
    indexes = [
        Index(name = "IDX_MEMBER_1", columnList = "NAME"),
        Index(name = "IDX_MEMBER_2", columnList = "EMAIL"),
    ],
)
@EntityListeners(LoggingListener::class)
class MemberEntity internal constructor(
    builder: MemberEntityBuilder,
) : ModifiableBaseEntity() {

    @Column(name = "NAME", nullable = false)
    var name: String = builder.name!!.value
        protected set

    @Column(name = "EMAIL", nullable = false)
    var email: String = builder.email!!.value
        protected set
}

class MemberEntityBuilder internal constructor(
    var name: Name? = null,
    var email: Email? = null,
) {

    internal fun build(): MemberEntity {
        requireNotNull(name) { "member.name is required" }
        requireNotNull(email) { "member.email is required" }
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
        this.name = Name("테스터")
        this.email = Email("tester@gmail.com")
    },
): MemberEntity = builder.apply(buildToAction).build()