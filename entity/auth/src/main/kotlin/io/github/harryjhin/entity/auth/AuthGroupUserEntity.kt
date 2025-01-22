package io.github.harryjhin.entity.auth

import io.github.harryjhin.common.id.AuthGroupId
import io.github.harryjhin.common.id.MemberId
import io.github.harryjhin.entity.core.CreatableBaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.Index
import jakarta.persistence.Table

@Entity
@Table(
    name = "auth_group_user",
    indexes = [
        Index(name = "auth_group_user_idx1", columnList = "auth_group_id, member_id", unique = true),
    ],
)
class AuthGroupUserEntity(
    builder: AuthGroupUserEntityBuilder,
) : CreatableBaseEntity() {

    var authGroupId: AuthGroupId = builder.authGroupId!!
        protected set

    var memberId: MemberId = builder.memberId!!
        protected set
}

class AuthGroupUserEntityBuilder internal constructor(
    var authGroupId: AuthGroupId? = null,
    var memberId: MemberId? = null,
) {
    internal fun build(): AuthGroupUserEntity {
        return AuthGroupUserEntity(this)
    }
}

fun AuthGroupUserEntity(
    builder: AuthGroupUserEntityBuilder = AuthGroupUserEntityBuilder(),
    buildToAction: AuthGroupUserEntityBuilder.() -> Unit = {},
): AuthGroupUserEntity = builder.apply(buildToAction).build()