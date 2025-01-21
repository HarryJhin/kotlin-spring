import io.github.harryjhin.api
import io.github.harryjhin.configureJar
import io.github.harryjhin.implementation
import io.github.harryjhin.plugins
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KotlinSpringDomainLibraryPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            plugins(
                "kotlin.spring.querydsl.jpa.library",
            )

            configureJar("domain")

            dependencies {
                api(project(":domain:core"))
                implementation(project(":infra:event"))
            }
        }
    }
}