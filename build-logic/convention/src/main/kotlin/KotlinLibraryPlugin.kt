import io.github.harryjhin.configureKotlinJvm
import io.github.harryjhin.plugins
import org.gradle.api.Plugin
import org.gradle.api.Project

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