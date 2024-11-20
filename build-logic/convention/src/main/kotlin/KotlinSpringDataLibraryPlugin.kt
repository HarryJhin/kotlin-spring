import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KotlinSpringDataLibraryPlugin : Plugin<Project> {
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

            dependencies {
                add("api", project(":entity:core"))
                add("api", project(":model:core"))
                add("implementation", project(":infra:database"))
                add("implementation", libs.findLibrary("spring.boot.starter.data.jpa").get())
            }
        }
    }
}