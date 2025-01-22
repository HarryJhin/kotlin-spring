plugins {
    alias(libs.plugins.kotlin.spring.domain.library)
    alias(libs.plugins.kotlin.spring.test)
}

group = "io.github.harryjhin.domain.security"

dependencies {
    api(projects.common)
    implementation(projects.entity.auth)
    implementation(projects.entity.member)
}