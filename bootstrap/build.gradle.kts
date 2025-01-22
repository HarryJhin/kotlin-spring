plugins {
    alias(libs.plugins.kotlin.spring.library)
}

group = "io.github.harryjhin.bootstrap"

dependencies {
    api(projects.common)
    api(libs.spring.data.commons)
}