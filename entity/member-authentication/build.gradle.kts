plugins {
    alias(libs.plugins.kotlin.spring.data.library)
}

group = "io.github.harryjhin.entity.member-authentication"

dependencies {
    api(projects.model.member)
    api(projects.model.password)
}
