plugins {
    alias(libs.plugins.kotlin.spring.library)
}

group = "io.github.harryjhin.data.database-tester"

dependencies {
    runtimeOnly(libs.h2)
    implementation(libs.spring.boot.starter.data.jpa)
}
