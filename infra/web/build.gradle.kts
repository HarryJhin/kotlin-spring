plugins {
    alias(libs.plugins.kotlin.spring.infra.library)
}

group = "io.github.harryjhin.infra.web"

dependencies {
    implementation(projects.common)
    implementation(projects.entity.core)
    implementation(libs.jackson.databind)
    implementation(libs.jackson.module.kotlin)
}