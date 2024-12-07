package io.github.harryjhin.entity.password

import io.github.harryjhin.entity.core.ModifiableBaseEntity
import io.github.harryjhin.model.member.MemberId
import io.github.harryjhin.model.password.EncodedPassword
import io.github.harryjhin.model.password.PasswordStrength
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Index
import jakarta.persistence.Table
import org.hibernate.annotations.Comment

@Entity
@Table(
    name = "PASSWORD",
    indexes = [
        Index(name = "IDX_PASSWORD_1", columnList = "MEMBER_ID", unique = true),
        Index(name = "IDX_PASSWORD_2", columnList = "STRENGTH"),
        Index(name = "IDX_PASSWORD_3", columnList = "UPDATED_AT"),
    ],
)
class PasswordEntity internal constructor(
    builder: PasswordEntityBuilder,
) : ModifiableBaseEntity() {

    @Comment("회원 ID")
    @Column(name = "MEMBER_ID", nullable = false)
    var memberId: Long = builder.memberId!!.value
        protected set

    @Comment("비밀번호 강도")
    @Column(name = "STRENGTH", nullable = false)
    var strength: Int = builder.strength!!.value
        protected set

    @Comment("비밀번호")
    @Column(name = "PASSWORD", nullable = false, length = 60)
    var password: String = builder.encodedPassword!!.value
        protected set
}

class PasswordEntityBuilder internal constructor(
    var memberId: MemberId? = null,
    var strength: PasswordStrength? = null,
    var encodedPassword: EncodedPassword? = null,
) {

    internal fun build(): PasswordEntity {
        require(memberId != null) { "`member` 프로퍼티는 필수입니다." }
        require(encodedPassword != null) { "`rawPassword` 프로퍼티는 필수입니다." }
        return PasswordEntity(this)
    }
}

fun PasswordEntity(
    builder: PasswordEntityBuilder = PasswordEntityBuilder(),
    buildToAction: PasswordEntityBuilder.() -> Unit,
): PasswordEntity = builder.apply(buildToAction).build()
