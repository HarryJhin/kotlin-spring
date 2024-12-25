plugins {
    alias(libs.plugins.kotlin.spring.domain.library)
    alias(libs.plugins.kotlin.spring.test)
}

group = "io.github.harryjhin.domain.member"

dependencies {
    api(projects.model)
    implementation(projects.entity.memberInfo)
    implementation(projects.entity.memberAuthentication)
    implementation(projects.entity.memberAuthorization)

    implementation(libs.spring.security.crypto)
}
