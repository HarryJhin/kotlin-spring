plugins {
    alias(libs.plugins.kotlin.spring.library)
}

group = "io.github.harryjhin.entity.core"

dependencies {
    implementation(projects.infra.database)
    api(libs.spring.boot.starter.data.jpa)
}
