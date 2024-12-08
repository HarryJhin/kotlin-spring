plugins {
    alias(libs.plugins.kotlin.spring.library)
    alias(libs.plugins.kotlin.kapt)
}

group = "io.github.harryjhin.data.database"

dependencies {
    runtimeOnly(libs.postgresql)
    implementation(libs.spring.boot.starter.data.jpa)

    kapt(libs.spring.boot.configuration.processor)
}
