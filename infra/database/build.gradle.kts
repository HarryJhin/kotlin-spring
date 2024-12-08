plugins {
    alias(libs.plugins.kotlin.spring.library)
}

group = "io.github.harryjhin.data.database"

dependencies {
    runtimeOnly(libs.postgresql)
    implementation(libs.spring.boot.starter.data.jpa)
}
