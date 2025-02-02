plugins {
    alias(libs.plugins.kotlin.spring.domain.library)
    alias(libs.plugins.kotlin.spring.test)
}

group = "io.github.harryjhin.domain.security"

dependencies {
    implementation(projects.entity.auth)
    implementation(projects.entity.member)
    implementation(libs.jackson.databind)
    implementation(libs.jjwt)
    implementation(libs.jakarta.validation.api)
    implementation(libs.tomcat.embed.core)
    implementation(libs.spring.boot.starter.security)
}