package io.github.harryjhin

import java.util.Optional
import org.gradle.api.Action
import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.provider.Provider

internal fun DependencyHandler.implementation(dependencyNotation: Optional<Provider<MinimalExternalModuleDependency>>) {
    addProvider("implementation", dependencyNotation.get())
}

internal fun DependencyHandler.implementation(dependencyNotation: Optional<Provider<MinimalExternalModuleDependency>>, dependencyConfiguration: Action<ExternalModuleDependency>) {
    addProvider("implementation", dependencyNotation.get(), dependencyConfiguration)
}

internal fun DependencyHandler.kapt(dependencyNotation: Optional<Provider<MinimalExternalModuleDependency>>) {
    addProvider("kapt", dependencyNotation.get())
}

internal fun DependencyHandler.kapt(dependencyNotation: Optional<Provider<MinimalExternalModuleDependency>>, dependencyConfiguration: Action<ExternalModuleDependency>) {
    addProvider("kapt", dependencyNotation.get(), dependencyConfiguration)
}