plugins {
    alias(libs.plugins.kotlin.spring.app)
}

group = "io.github.harryjhin"
version = "0.0.1-SNAPSHOT"

dependencies {
    implementation(libs.spring.boot.starter.web)
    implementation(libs.jackson.module.kotlin)
    implementation(libs.kotlin.reflect)
    testImplementation(libs.spring.boot.starter.test)
    testImplementation(libs.kotlin.test.junit5)
    testRuntimeOnly(libs.junit.platform.launcher)
}
