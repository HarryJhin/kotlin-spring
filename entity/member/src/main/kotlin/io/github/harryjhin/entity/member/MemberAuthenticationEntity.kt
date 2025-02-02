package io.github.harryjhin.entity.member

import io.github.harryjhin.entity.core.ModifiableBaseEntity
import io.github.harryjhin.common.member.EncodedPassword
import io.github.harryjhin.common.id.MemberId
import io.github.harryjhin.common.member.PasswordStrength
import io.github.harryjhin.common.member.Username
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(
    name = "MEMBER_AUTHENTICATION",
    indexes = [],
)
class MemberAuthenticationEntity internal constructor(
    builder: MemberAuthenticationEntityBuilder,
) : ModifiableBaseEntity() {

    @Column(name = "MEMBER_ID", nullable = false)
    var memberId: MemberId = builder.memberId!!
        protected set

    @Column(name = "USERNAME", nullable = false)
    var username: Username = builder.username!!
        protected set

    @Column(name = "STRENGTH", nullable = false)
    var strength: PasswordStrength = builder.strength!!
        protected set

    @Column(name = "PASSWORD", nullable = false)
    var password: EncodedPassword = builder.password!!
        protected set
}

class MemberAuthenticationEntityBuilder internal constructor(
    var memberId: MemberId? = null,
    var username: Username? = null,
    var strength: PasswordStrength? = null,
    var password: EncodedPassword? = null,
) {
    internal fun build(): MemberAuthenticationEntity {
        requireNotNull(memberId) { "member_authentication.member_id is required" }
        requireNotNull(username) { "member_authentication.username is required" }
        requireNotNull(strength) { "member_authentication.strength is required" }
        requireNotNull(password) { "member_authentication.password is required" }
        return MemberAuthenticationEntity(this)
    }
}

fun MemberAuthenticationEntity(
    builder: MemberAuthenticationEntityBuilder = MemberAuthenticationEntityBuilder(),
    buildToAction: MemberAuthenticationEntityBuilder.() -> Unit = {},
): MemberAuthenticationEntity = builder.apply(buildToAction).build()