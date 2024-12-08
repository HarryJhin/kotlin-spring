plugins {
    alias(libs.plugins.kotlin.spring.library)
}

group = "io.github.harryjhin.data.database-test"

dependencies {
    runtimeOnly(libs.h2)
    implementation(libs.spring.boot.starter.data.jpa)
}
