plugins {
    alias(libs.plugins.kotlin.spring.library)
}

group = "io.github.harryjhin.infra.spring-boot-tester"

dependencies {
    implementation(projects.infra.databaseTester)
    implementation(libs.spring.boot.autoconfigure)
}
