plugins {
    alias(libs.plugins.kotlin.spring.domain.library)
    alias(libs.plugins.kotlin.spring.test)
}

group = "io.github.harryjhin.domain.member"

dependencies {
    api(projects.model.core)
    api(projects.model.member)
    implementation(projects.entity.member)
    implementation(projects.entity.memberAuthentication)
    implementation(projects.entity.memberAuthorization)

    implementation(libs.spring.security.crypto)
}
