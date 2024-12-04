plugins {
    alias(libs.plugins.kotlin.spring.library)
    alias(libs.plugins.kotlin.spring.test)
}

group = "io.github.harryjhin.domain.member"

dependencies {
    api(projects.model.core)
    api(projects.model.member)
    implementation(projects.entity.member)
}
