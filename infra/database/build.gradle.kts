plugins {
    alias(libs.plugins.kotlin.spring.infra.library)
    alias(libs.plugins.kotlin.kapt)
}

group = "io.github.harryjhin.data.database"

dependencies {
    runtimeOnly(libs.postgresql)
    implementation(libs.spring.boot.starter.data.jpa)
    implementation(libs.spring.retry)

    kapt(libs.spring.boot.configuration.processor)
}