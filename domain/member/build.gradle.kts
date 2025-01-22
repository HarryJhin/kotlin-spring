plugins {
    alias(libs.plugins.kotlin.spring.domain.library)
    alias(libs.plugins.kotlin.spring.test)
}

group = "io.github.harryjhin.domain.member"

dependencies {
    api(projects.common)
    implementation(projects.entity.member)

    implementation(libs.spring.security.crypto)
}
