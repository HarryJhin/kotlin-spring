plugins {
    alias(libs.plugins.kotlin.spring.data.library)
}

group = "io.github.harryjhin.entity.member-authorization"

dependencies {
    api(projects.model)
}
