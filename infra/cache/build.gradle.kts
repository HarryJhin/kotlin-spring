plugins {
    alias(libs.plugins.kotlin.spring.library)
}

group = "io.github.harryjhin.infra.cache"

dependencies {
    api(projects.infra.redis)
}
