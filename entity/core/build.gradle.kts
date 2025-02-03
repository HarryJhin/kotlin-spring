plugins {
    alias(libs.plugins.kotlin.spring.querydsl.jpa.library)
    alias(libs.plugins.kotlin.library.test)
}

group = "io.github.harryjhin.entity.core"

tasks.withType<Jar> {
    archiveBaseName = "entity-core"
    archiveClassifier = ""
}

dependencies {
    implementation(projects.entity.log)
}
