import org.gradle.api.Plugin
import org.gradle.api.Project

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
        }
    }
}