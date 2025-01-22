import io.github.harryjhin.configureJar
import io.github.harryjhin.disableBootJar
import io.github.harryjhin.implementation
import io.github.harryjhin.libs
import io.github.harryjhin.plugins
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KotlinSpringInfraLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            plugins(
                "kotlin.spring.library",
            )

            disableBootJar()
            configureJar("infra")

            dependencies {
            }
        }
    }
}