package io.github.harryjhin.entity.auth

import io.github.harryjhin.common.exception.valid
import io.github.harryjhin.entity.core.ModifiableBaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "auth_group")
class AuthGroupEntity(
    builder: AuthGroupEntityBuilder
) : ModifiableBaseEntity() {

    var name: String = builder.name!!
        protected set

    var description: String = builder.description!!
        protected set

    var isWritable: Boolean = builder.isWritable!!
        protected set

    var isReadable: Boolean = builder.isReadable!!
        protected set

    var isUpdatable: Boolean = builder.isUpdatable!!
        protected set

    var isDeletable: Boolean = builder.isDeletable!!
        protected set
}

class AuthGroupEntityBuilder internal constructor(
    var name: String? = null,
    var description: String? = null,
    var isWritable: Boolean? = null,
    var isReadable: Boolean? = null,
    var isUpdatable: Boolean? = null,
    var isDeletable: Boolean? = null,
) {
    internal fun build(): AuthGroupEntity {
        valid(name != null) { "auth_group.name.not-null" }
        valid(description != null) { "auth_group.description.not-null" }
        valid(isWritable != null) { "auth_group.is-writable.not-null" }
        valid(isReadable != null) { "auth_group.is-readable.not-null" }
        valid(isUpdatable != null) { "auth_group.is-updatable.not-null" }
        valid(isDeletable != null) { "auth_group.is-deletable.not-null" }
        return AuthGroupEntity(this)
    }
}

fun AuthGroupEntity(
builder: AuthGroupEntityBuilder = AuthGroupEntityBuilder(),
    buildToAction: AuthGroupEntityBuilder.() -> Unit
): AuthGroupEntity {
    return builder.apply(buildToAction).build()
}