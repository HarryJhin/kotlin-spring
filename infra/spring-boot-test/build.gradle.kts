plugins {
    alias(libs.plugins.kotlin.spring.library)
}

group = "io.github.harryjhin.infra.spring-boot-test"

dependencies {
    implementation(projects.infra.databaseTest)
    implementation(libs.spring.boot.autoconfigure)
}
