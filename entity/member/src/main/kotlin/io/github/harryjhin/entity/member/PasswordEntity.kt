package io.github.harryjhin.entity.member

import io.github.harryjhin.entity.core.CreatableBaseEntity
import io.github.harryjhin.model.member.password.EncodedPassword
import io.github.harryjhin.model.member.password.RawPassword
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Index
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.Comment

@Entity
@Table(
    name = "PASSWORD",
    indexes = [
        Index(name = "IDX_PASSWORD_1", columnList = "MEMBER_ID,CREATED_AT"),
    ],
)
class PasswordEntity internal constructor(
    builder: PasswordEntityBuilder,
) : CreatableBaseEntity<Long>() {

    @Comment("회원 ID")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    var member: MemberEntity = builder.member!!
        protected set

    @Comment("비밀번호")
    @Column(name = "PASSWORD", nullable = false, length = 60)
    var password: String = builder.encodedPassword.value
        protected set
}

class PasswordEntityBuilder internal constructor(
    var member: MemberEntity? = null,
    var rawPassword: RawPassword? = null,
) {

    val encodedPassword: EncodedPassword
        get() {
            requireNotNull(rawPassword) { "`rawPassword` 프로퍼티는 필수입니다." }
            return rawPassword!!.encode()
        }

    internal fun build(): PasswordEntity {
        require(member != null) { "`member` 프로퍼티는 필수입니다." }
        require(rawPassword != null) { "`rawPassword` 프로퍼티는 필수입니다." }
        return PasswordEntity(this)
    }
}

fun PasswordEntity(
    builder: PasswordEntityBuilder = PasswordEntityBuilder(),
    buildToAction: PasswordEntityBuilder.() -> Unit,
): PasswordEntity = builder.apply(buildToAction).build()
