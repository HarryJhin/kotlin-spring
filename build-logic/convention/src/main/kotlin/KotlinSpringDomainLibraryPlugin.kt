import io.github.harryjhin.api
import io.github.harryjhin.configureJar
import io.github.harryjhin.disableBootJar
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

            disableBootJar()
            configureJar("domain")

            dependencies {
                api(project(":domain:core"))
                api(project(":bootstrap"))
                implementation(project(":infra:event"))
            }
        }
    }
}