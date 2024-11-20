import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KotlinSpringTestPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            plugins(
                "org.jetbrains.kotlin.jvm",
                "org.jetbrains.kotlin.plugin.spring",
                "org.springframework.boot",
                "io.spring.dependency-management",
            )

            configureKotlinJvm()
            configureTestTask()

            dependencies {
                add("testRuntimeOnly", libs.findLibrary("junit.platform.launcher").get())
                add("testImplementation", libs.findLibrary("kotlin.test.junit5").get())
                add("testImplementation", libs.findLibrary("spring.boot.starter.test").get())
            }
        }
    }
}