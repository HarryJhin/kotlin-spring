plugins {
    alias(libs.plugins.kotlin.spring.library)
}

group = "io.github.harryjhin.infra.redis-tester"

dependencies {
    implementation(libs.jakarta.annotation.api)
    implementation(libs.embedded.redis)
}