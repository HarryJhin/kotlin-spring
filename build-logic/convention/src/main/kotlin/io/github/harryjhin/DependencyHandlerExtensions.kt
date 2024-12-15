package io.github.harryjhin

import org.gradle.api.Action
import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.provider.Provider

internal fun DependencyHandler.implementation(dependencyNotation: Provider<*>) {
    addProvider("implementation", dependencyNotation)
}

internal fun DependencyHandler.implementation(dependencyNotation: Provider<*>, dependencyConfiguration: Action<ExternalModuleDependency>) {
    addProvider("implementation", dependencyNotation, dependencyConfiguration)
}

internal fun DependencyHandler.kapt(dependencyNotation: Provider<*>) {
    addProvider("kapt", dependencyNotation)
}

internal fun DependencyHandler.kapt(dependencyNotation: Provider<*>, dependencyConfiguration: Action<ExternalModuleDependency>) {
    addProvider("kapt", dependencyNotation, dependencyConfiguration)
}