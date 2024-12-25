plugins {
    alias(libs.plugins.kotlin.spring.entity.library)
}

group = "io.github.harryjhin.entity.member-authentication"

dependencies {
    api(projects.model)
}