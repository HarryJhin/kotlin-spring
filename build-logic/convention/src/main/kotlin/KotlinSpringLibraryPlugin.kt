import io.github.harryjhin.disableBootJar
import io.github.harryjhin.implementation
import io.github.harryjhin.libs
import io.github.harryjhin.plugins
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KotlinSpringLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            plugins(
                "kotlin.library",
                "org.jetbrains.kotlin.plugin.spring",
                "org.springframework.boot",
                "io.spring.dependency-management",
            )

            dependencies {
                implementation(libs.findLibrary("kotlin.reflect"))
                implementation(libs.findLibrary("spring.context"))
            }
        }
    }
}