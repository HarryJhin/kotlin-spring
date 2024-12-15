import io.github.harryjhin.configureKotlinJvm
import io.github.harryjhin.configureTestTask
import io.github.harryjhin.libs
import io.github.harryjhin.plugins
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KotlinLibraryTestPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            plugins(
                "java-library",
                "org.jetbrains.kotlin.jvm",
            )

            configureKotlinJvm()
            configureTestTask()

            dependencies {
                add("testRuntimeOnly", libs.findLibrary("junit.platform.launcher").get())
                add("testImplementation", libs.findLibrary("kotlin.test.junit5").get())
            }
        }
    }
}