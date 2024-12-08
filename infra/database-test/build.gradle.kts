plugins {
    alias(libs.plugins.kotlin.spring.library)
}

group = "io.github.harryjhin.data.database-test"

dependencies {
    implementation(libs.spring.boot.starter.data.jpa)
    runtimeOnly(libs.h2)
}
