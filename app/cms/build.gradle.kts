plugins {
    alias(libs.plugins.kotlin.spring.app)
    alias(libs.plugins.kotlin.spring.test)
}

group = "io.github.harryjhin.app.cms"

dependencies {
    implementation(libs.jackson.module.kotlin)
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.thymeleaf)
}