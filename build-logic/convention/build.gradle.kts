plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.spring.boot.gradle.plugin)
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
        register("kotlin-spring-domain-library") {
            id = "kotlin.spring.domain.library"
            implementationClass = "KotlinSpringDomainLibraryPlugin"
        }
        register("kotlin-spring-entity-library") {
            id = "kotlin.spring.entity.library"
            implementationClass = "KotlinSpringEntityLibraryPlugin"
        }
        register("kotlin-spring-infra-library") {
            id = "kotlin.spring.infra.library"
            implementationClass = "KotlinSpringInfraLibraryPlugin"
        }
        register("kotlin-spring-querydsl-jpa-library") {
            id = "kotlin.spring.querydsl.jpa.library"
            implementationClass = "KotlinSpringQuerydslJpaLibraryPlugin"
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