import io.github.harryjhin.configureKotlinJvm
import io.github.harryjhin.configureTestTask
import io.github.harryjhin.libs
import io.github.harryjhin.plugins
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KotlinSpringTestPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            plugins(
                "kotlin.spring.library",
                "kotlin.library.test",
            )

            dependencies {
                add("testImplementation", project(":infra:spring-boot-tester"))
                add("testImplementation", libs.findLibrary("kotlin.test.junit5").get())
                add("testImplementation", libs.findLibrary("spring.boot.starter.test").get())
                add("testRuntimeOnly", libs.findLibrary("junit.platform.launcher").get())
            }
        }
    }
}