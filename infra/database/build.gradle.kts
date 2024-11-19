plugins {
    alias(libs.plugins.kotlin.spring.library)
    alias(libs.plugins.kotlin.spring.test)
}

group = "io.github.harryjhin.data.database"

dependencies {
    runtimeOnly(libs.postgresql)
    implementation(libs.spring.boot.starter.data.jpa)
    testRuntimeOnly(libs.h2)
}
