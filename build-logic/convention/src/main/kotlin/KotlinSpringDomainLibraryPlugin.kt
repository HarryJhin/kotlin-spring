import io.github.harryjhin.configureBootJar
import io.github.harryjhin.configureJar
import io.github.harryjhin.configureKotlinJvm
import io.github.harryjhin.configureQueryDSL
import io.github.harryjhin.libs
import io.github.harryjhin.plugins
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KotlinSpringDomainLibraryPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            plugins(
                "java-library",
                "org.jetbrains.kotlin.jvm",
                "org.jetbrains.kotlin.plugin.jpa",
                "org.jetbrains.kotlin.plugin.spring",
                "org.springframework.boot",
                "io.spring.dependency-management",
            )

            configureKotlinJvm()
            configureBootJar(false)
            configureJar(true)
            configureQueryDSL()

            dependencies {
                add("api", project(":domain:core"))
                add("api", project(":model"))
                add("implementation", libs.findLibrary("kotlin.reflect").get())
                add("implementation", libs.findLibrary("spring.boot.starter.data.jpa").get())
            }
        }
    }
}