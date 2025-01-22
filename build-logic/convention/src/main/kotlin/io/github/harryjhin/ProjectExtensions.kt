package io.github.harryjhin

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.tasks.testing.Test
import org.gradle.jvm.tasks.Jar
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.springframework.boot.gradle.tasks.bundling.BootJar

internal val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal fun Project.plugins(vararg pluginId: String) {
    with(this.pluginManager) {
        pluginId.forEach(::apply)
    }
}

internal fun Project.configureKotlinJvm() {
    extensions.configure<KotlinJvmProjectExtension> {
        jvmToolchain(17)

        compilerOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
        }
    }
}

internal fun Project.configureTestTask() {
    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

internal fun Project.disableBootJar() {
    tasks.withType(Jar::class.java) {
        this.enabled = true
    }
    tasks.withType(BootJar::class.java) {
        this.enabled = false
    }
}

internal fun Project.configureJar(prefix: String = "") {
    tasks.withType<Jar> {
        this.archiveBaseName = "${prefix}-${this@configureJar.name}"
        this.archiveClassifier = ""
    }
}

internal fun Project.configureQueryDSL() {
    pluginManager.apply("org.jetbrains.kotlin.kapt")

    dependencies {
        // JDK 17부터 javax 패키지가 jakarta 패키지로 변경됨
        implementation(libs.findLibrary("querydsl.jpa")) {
            artifact {
                classifier = "jakarta"
            }
        }
        implementation(libs.findLibrary("querydsl.apt")) {
            artifact {
                classifier = "jakarta"
            }
        }
        implementation(libs.findLibrary("jakarta-persistence.api"))
        implementation(libs.findLibrary("jakarta.annotation.api"))
        kapt(libs.findLibrary("spring.boot.configuration.processor"))
        kapt(libs.findLibrary("querydsl.apt")) {
            artifact {
                classifier = "jakarta"
            }
        }
    }
}