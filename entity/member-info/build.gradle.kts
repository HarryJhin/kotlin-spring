plugins {
    alias(libs.plugins.kotlin.spring.entity.library)
}

group = "io.github.harryjhin.entity.member"

dependencies {
    api(projects.model)
}
