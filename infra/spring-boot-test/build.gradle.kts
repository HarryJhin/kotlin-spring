plugins {
    alias(libs.plugins.kotlin.spring.library)
}

group = "io.github.harryjhin.data.spring-boot-test"

dependencies {
    implementation(libs.spring.boot.autoconfigure)
}
