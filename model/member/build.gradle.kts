plugins {
    alias(libs.plugins.kotlin.spring.library)
    alias(libs.plugins.kotlin.library.test)
}

group = "io.github.harryjhin.model.member"

dependencies {
    implementation(libs.spring.security.crypto)
}
