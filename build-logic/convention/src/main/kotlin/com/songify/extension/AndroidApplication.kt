package com.songify.extension

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

fun Project.androidApplication() {
    configure<ApplicationExtension> {
        compileSdk = libs.versions.compileSdk.get().toInt()

        defaultConfig {
            minSdk = libs.versions.minSdk.get().toInt()
            targetSdk = libs.versions.targetSdk.get().toInt()

            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

            versionCode = 1
            versionName = "1.0"
            applicationId = "com.songify"
            testApplicationId = "com.songify.test"
            manifestPlaceholders.putAll(
                mapOf(
                    "redirectSchemeName" to "songify",
                    "redirectHostName" to "callback"
                )
            )
        }

        signingConfigs {
            maybeCreate("debug").apply {
                storeFile = file("debug.keystore")
                storePassword = "android"
                keyAlias = "androiddebugkey"
                keyPassword = "android"

            }
        }

        buildTypes {
            debug {
                isDebuggable = true
                signingConfig = signingConfigs.getByName("debug")
            }
        }

        testOptions {
            unitTests.all {
                // Explicitly disable stacktrace recovery during test runs so that tests can rely on
                // Exception referential equality when asserting that the actual Exception thrown is equal
                // to the expected Exception.
                // For more info, please see:
                //   - https://github.com/Kotlin/kotlinx.coroutines/blob/master/docs/debugging.md#stacktrace-recovery
                //   - https://github.com/Kotlin/kotlinx.coroutines/issues/921
                it.systemProperty("kotlinx.coroutines.stacktrace.recovery", "false")
            }

            execution = "ANDROIDX_TEST_ORCHESTRATOR"
        }

        buildConfigFields()
    }

    tasks.withType<KotlinCompile>().configureEach {
        compilerOptions {
            freeCompilerArgs.addAll(
                "-opt-in=androidx.compose.foundation.ExperimentalFoundationApi",
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-opt-in=kotlinx.coroutines.FlowPreview",
                "-Xjsr305=strict"
            )
        }
    }
}

fun Project.buildConfigFields() {
    configure<ApplicationExtension> {
        defaultConfig {
            // buildConfigField("int", "BUILD_NUMBER", "" + project.extra["buildNumber"] + "")
        }
    }
}
