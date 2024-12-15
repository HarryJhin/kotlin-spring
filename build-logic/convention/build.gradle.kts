plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.kotlin.gradle.plugin)
}

kotlin {
    jvmToolchain(17)
}

gradlePlugin {
    plugins {
        register("kotlin-library") {
            id = "kotlin.library"
            implementationClass = "KotlinLibraryPlugin"
        }
        register("kotlin-library-test") {
            id = "kotlin.library.test"
            implementationClass = "KotlinLibraryTestPlugin"
        }
        register("kotlin-spring-app") {
            id = "kotlin.spring.app"
            implementationClass = "KotlinSpringAppPlugin"
        }
        register("kotlin-spring-data-library") {
            id = "kotlin.spring.data.library"
            implementationClass = "KotlinSpringDataLibraryPlugin"
        }
        register("kotlin-spring-domain-library") {
            id = "kotlin.spring.domain.library"
            implementationClass = "KotlinSpringDomainLibraryPlugin"
        }
        register("kotlin-spring-library") {
            id = "kotlin.spring.library"
            implementationClass = "KotlinSpringLibraryPlugin"
        }
        register("kotlin-spring-test") {
            id = "kotlin.spring.test"
            implementationClass = "KotlinSpringTestPlugin"
        }
    }
}