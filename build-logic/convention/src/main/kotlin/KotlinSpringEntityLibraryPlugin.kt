import io.github.harryjhin.api
import io.github.harryjhin.disableBootJar
import io.github.harryjhin.configureJar
import io.github.harryjhin.implementation
import io.github.harryjhin.plugins
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KotlinSpringEntityLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            plugins(
                "kotlin.spring.querydsl.jpa.library",
            )

            disableBootJar()
            configureJar("entity")

            dependencies {
                api(project(":entity:core"))
                implementation(project(":infra:event"))
            }
        }
    }
}