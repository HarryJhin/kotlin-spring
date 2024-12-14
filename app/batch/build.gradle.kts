plugins {
    alias(libs.plugins.kotlin.spring.app)
    alias(libs.plugins.kotlin.spring.test)
}

group = "io.github.harryjhin.app.batch"

dependencies {
    implementation(libs.spring.boot.starter.batch)
    testImplementation(libs.spring.batch.test)
}