package com.songify.extension

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

fun Project.platform() {
    val catalog = extensions.getByType<VersionCatalogsExtension>().named("libs")
    dependencies {
        constraints {
            catalog.libraryAliases.forEach { alias ->
                "implementation"(catalog.findLibrary(alias).get())
            }
        }
    }
}
