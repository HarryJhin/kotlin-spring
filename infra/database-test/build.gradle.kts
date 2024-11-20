plugins {
    alias(libs.plugins.kotlin.spring.library)
    alias(libs.plugins.kotlin.spring.test)
}

group = "io.github.harryjhin.data.database-test"

dependencies {
    runtimeOnly(libs.h2)
}
