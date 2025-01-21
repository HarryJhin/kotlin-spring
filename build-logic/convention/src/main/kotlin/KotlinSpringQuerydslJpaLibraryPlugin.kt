import io.github.harryjhin.api
import io.github.harryjhin.configureBootJar
import io.github.harryjhin.configureQueryDSL
import io.github.harryjhin.implementation
import io.github.harryjhin.libs
import io.github.harryjhin.plugins
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KotlinSpringQuerydslJpaLibraryPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            plugins(
                "org.jetbrains.kotlin.plugin.jpa",
                "kotlin.spring.library",
            )

            configureBootJar(false)
            configureQueryDSL()

            dependencies {
                api(project(":model"))
                implementation(project(":infra:event"))

                implementation(libs.findLibrary("kotlin.reflect"))
                implementation(libs.findLibrary("spring.boot.starter.data.jpa"))
            }
        }
    }
}