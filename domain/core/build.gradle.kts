plugins {
    alias(libs.plugins.kotlin.spring.library)
    alias(libs.plugins.kotlin.kapt)
}

group = "io.github.harryjhin.domain.core"

dependencies {
    implementation(libs.spring.boot.starter.data.jpa)
    implementation(libs.querydsl.jpa) {
        artifact {
            classifier = "jakarta"
        }
    }
    implementation(libs.querydsl.apt) {
        artifact {
            classifier = "jakarta"
        }
    }
    implementation(libs.jakarta.annotation.api)
    implementation(libs.jakarta.persistence.api)

    kapt(libs.querydsl.apt) {
        artifact {
            classifier = "jakarta"
        }
    }
    kapt(libs.spring.boot.configuration.processor)
}
