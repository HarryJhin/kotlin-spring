plugins {
    alias(libs.plugins.kotlin.spring.querydsl.jpa.library)
}

group = "io.github.harryjhin.entity.log"

tasks.withType<Jar> {
    archiveBaseName = "entity-log"
    archiveClassifier = ""
}
