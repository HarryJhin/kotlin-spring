package io.github.harryjhin.entity.member.authorization

import io.github.harryjhin.entity.core.ModifiableBaseEntity
import io.github.harryjhin.model.member.MemberId
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(
    name = "MEMBER_AUTHORIZATION",
    indexes = [],
)
class MemberAuthorizationEntity internal constructor(
    builder: MemberAuthorizationEntityBuilder,
) : ModifiableBaseEntity() {

    @Column(name = "MEMBER_ID", nullable = false)
    var memberId: Long = builder.memberId!!.value
        protected set
}

class MemberAuthorizationEntityBuilder internal constructor(
    var memberId: MemberId? = null,
) {
    fun build(): MemberAuthorizationEntity {
        requireNotNull(memberId) { "member_authorization.member_id is required" }
        return MemberAuthorizationEntity(this)
    }
}

fun MemberAuthorizationEntity(
    builder: MemberAuthorizationEntityBuilder = MemberAuthorizationEntityBuilder(),
    buildToAction: MemberAuthorizationEntityBuilder.() -> Unit = {},
): MemberAuthorizationEntity = builder.apply(buildToAction).build()
