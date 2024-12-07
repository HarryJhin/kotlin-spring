import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KotlinLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            plugins(
                "java-library",
                "org.jetbrains.kotlin.jvm",
            )

            configureKotlinJvm()
        }
    }
}