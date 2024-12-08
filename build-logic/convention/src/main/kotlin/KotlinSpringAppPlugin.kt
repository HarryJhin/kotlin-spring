import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KotlinSpringAppPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            plugins(
                "org.jetbrains.kotlin.jvm",
                "org.jetbrains.kotlin.plugin.spring",
                "org.springframework.boot",
                "io.spring.dependency-management",
            )

            configureKotlinJvm()
            configureBootJar(true)
            configureJar(false)

            dependencies {
                add("implementation", project(":model:core"))

                add("implementation", libs.findLibrary("kotlin.reflect").get())

                add("developmentOnly", libs.findLibrary("spring.boot.devtools").get())
            }
        }
    }
}
