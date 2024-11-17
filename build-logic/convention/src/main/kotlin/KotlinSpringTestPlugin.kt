import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KotlinSpringTestPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            configureTestTask()

            dependencies {
                add("testImplementation", libs.findLibrary("spring-boot-starter-test").get())
            }
        }
    }
}