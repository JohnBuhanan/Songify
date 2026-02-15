package com.songify.extension

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

fun Project.androidLibrary() {
    configure<LibraryExtension> {
        compileSdk = libs.versions.compileSdk.get().toInt()

        defaultConfig {
            minSdk = libs.versions.minSdk.get().toInt()
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_21
            targetCompatibility = JavaVersion.VERSION_21
        }

        sourceSets {
            getByName("main") {
                java.srcDir("src/main/kotlin")
            }
            getByName("test") {
                java.srcDir("src/test/kotlin")
            }
        }
    }

    // Apply common Kotlin options
    tasks.withType<KotlinCompile>().configureEach {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
            freeCompilerArgs.addAll(
                "-opt-in=androidx.compose.foundation.ExperimentalFoundationApi",
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-opt-in=kotlinx.coroutines.FlowPreview",
                "-Xjsr305=strict"
            )
        }
    }
}

fun Project.androidLibrary(androidBlock: LibraryExtension.() -> Unit) {
    extensions.findByType(LibraryExtension::class.java)?.apply(androidBlock)
}

fun Project.androidApplication(androidBlock: ApplicationExtension.() -> Unit) {
    extensions.findByType(ApplicationExtension::class.java)?.apply(androidBlock)
}

fun Project.android(androidBlock: CommonExtension.() -> Unit) {
    extensions.findByType(CommonExtension::class.java)?.apply(androidBlock)
}
