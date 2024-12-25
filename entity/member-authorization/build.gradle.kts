plugins {
    alias(libs.plugins.kotlin.spring.entity.library)
}

group = "io.github.harryjhin.entity.member-authorization"

dependencies {
    api(projects.model)
}
