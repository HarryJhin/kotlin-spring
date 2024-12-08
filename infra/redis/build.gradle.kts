plugins {
    alias(libs.plugins.kotlin.spring.library)
}

group = "io.github.harryjhin.infra.redis"

dependencies {
    implementation(libs.spring.boot.starter.data.redis)
}
