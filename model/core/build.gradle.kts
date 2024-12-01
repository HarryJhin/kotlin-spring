plugins {
    alias(libs.plugins.kotlin.library)
    alias(libs.plugins.kotlin.library.test)
}

group = "io.github.harryjhin.model.core"

dependencies {
    implementation(libs.kotlin.reflect)
}
