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
        register("kotlin-spring-app") {
            id = "kotlin.spring.app"
            implementationClass = "KotlinSpringAppPlugin"
        }
        register("kotlin-spring-data-library") {
            id = "kotlin.spring.data.library"
            implementationClass = "KotlinSpringDataLibraryPlugin"
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