package com.songify.extension

import com.google.devtools.ksp.gradle.KspExtension
import com.google.devtools.ksp.gradle.KspGradleSubplugin
import dev.zacsweers.metro.gradle.MetroGradleSubplugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType

fun Project.metro() {
    applyOnce<MetroGradleSubplugin>()

    plugins.withType<KspGradleSubplugin> {
        configure<KspExtension> {
            arg("circuit.codegen.mode", "metro")
        }
    }

    dependencies {
        "implementation"(libs.metro.runtime)
    }
}
