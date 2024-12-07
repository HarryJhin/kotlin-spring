plugins {
    alias(libs.plugins.kotlin.spring.app)
    alias(libs.plugins.kotlin.spring.test)
}

group = "io.github.harryjhin.app.api"
version = "0.0.1-SNAPSHOT"

dependencies {
    implementation(projects.domain.member)

    implementation(libs.spring.boot.starter.logging)
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.tx)
    implementation(libs.jackson.module.kotlin)
}
