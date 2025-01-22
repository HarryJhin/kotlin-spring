plugins {
    alias(libs.plugins.kotlin.spring.querydsl.jpa.library)
}

group = "io.github.harryjhin.domain.core"

dependencies {
    api(libs.spring.web)
}

tasks.withType<Jar> {
    archiveBaseName = "domain-core"
    archiveClassifier = ""
}
