plugins {
    alias(libs.plugins.kotlin.spring.library)
}

group = "io.github.harryjhin.infra.cache-tester"

dependencies {
    api(projects.infra.redisTester)
}
