plugins {
    alias(libs.plugins.kotlin.spring.library)
}

group = "io.github.harryjhin.data.database-test"

dependencies {
    implementation(libs.spring.data.jpa)
    runtimeOnly(libs.h2)
}
