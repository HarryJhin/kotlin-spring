plugins {
    alias(libs.plugins.kotlin.spring.library)
    alias(libs.plugins.kotlin.spring.test)
    alias(libs.plugins.kotlin.kapt)
}

group = "io.github.harryjhin.domain.member"

dependencies {
    api(projects.model.core)
    api(projects.model.member)
    api(projects.model.password)
    implementation(projects.entity.member)
    implementation(projects.entity.password)

    implementation(libs.spring.security.crypto)

    kapt(libs.spring.boot.configuration.processor)
}
