plugins {
    alias(libs.plugins.kotlin.spring.data.library)
}

group = "io.github.harryjhin.entity.password"

dependencies {
    api(projects.model.member)
    api(projects.model.password)
}
