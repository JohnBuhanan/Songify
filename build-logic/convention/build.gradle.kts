plugins {
    `kotlin-dsl`
}

apply(from = "../kotlin.gradle")

dependencies {
    implementation(libs.affectedModuleDetector)
    implementation(libs.androidGradlePlugin)
    implementation(libs.composeCompiler.gradlePlugin)
    implementation(libs.detektGradlePlugin)
    implementation(libs.graphAssert)
    implementation(libs.kotlin.gradle)
    implementation(libs.kspGradlePlugin)
    implementation(libs.metro.gradle.plugin)
    implementation(libs.moduleGraph)
    implementation(libs.sortDependenciesGradlePlugin)
    implementation(libs.spotlight)

    implementation(localGroovy())
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

gradlePlugin {
    plugins {
        register("songifySettings") {
            id = "songify.settings"
            implementationClass = "SongifySettingsPlugin"
        }
        register("songifyRoot") {
            id = "songify.root"
            implementationClass = "SongifyRootPlugin"
        }
        register("songifyAndroidApplication") {
            id = "songify.android.application"
            implementationClass = "SongifyAndroidApplicationPlugin"
        }
        register("songifyAndroidLibrary") {
            id = "songify.android.library"
            implementationClass = "SongifyAndroidLibraryPlugin"
        }
        register("songifyKotlinLibrary") {
            id = "songify.kotlin.library"
            implementationClass = "SongifyKotlinLibraryPlugin"
        }
    }
}
