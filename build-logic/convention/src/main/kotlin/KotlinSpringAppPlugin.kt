import io.github.harryjhin.*
import io.github.harryjhin.libs
import io.github.harryjhin.plugins
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KotlinSpringAppPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            plugins(
                "kotlin.spring.library",
            )

            dependencies {
                implementation(project(":common"))
                implementation(project(":infra:cache"))
                implementation(project(":infra:database"))
                implementation(project(":infra:event"))
                implementation(project(":infra:redis"))

                implementation(libs.findLibrary("spring.boot.starter.actuator"))

                developmentOnly(libs.findLibrary("spring.boot.devtools"))
            }
        }
    }
}