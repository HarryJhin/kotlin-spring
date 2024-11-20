pluginManagement {
    includeBuild("build-logic")
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

rootProject.name = "kotlin-spring"
includeModules("app")
includeModules("domain")
includeModules("entity")
includeModules("infra")
includeModules("model")

fun includeModules(prefix: String) {
    File(rootDir, prefix).listFiles()
        ?.filter { file: File -> file.isDirectory && File(file, "build.gradle.kts").exists() }
        ?.map { file: File -> ":$prefix:${file.name}" }
        ?.toTypedArray()
        ?.run { include(*this) }
}