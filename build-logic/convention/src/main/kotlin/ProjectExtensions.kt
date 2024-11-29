import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

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

internal fun Project.configureBootJar(enabled: Boolean = false) {
    tasks.named("bootJar") {
        this.enabled = enabled
    }
}

internal fun Project.configureJar(enabled: Boolean = false) {
    tasks.named("jar") {
        this.enabled = enabled
    }
}
