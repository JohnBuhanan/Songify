import com.android.build.gradle.AppPlugin
import com.songify.extension.androidApplication
import com.songify.extension.applyOnce
import com.songify.extension.circuit
import com.songify.extension.configureLint
import com.songify.extension.detekt
import com.songify.extension.dynamicNamespace
import com.songify.extension.graphAssert
import com.songify.extension.jetpackCompose
import com.songify.extension.libs
import com.songify.extension.metro
import com.songify.extension.moduleNameFix
import com.songify.extension.moshi
import com.songify.extension.platform
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class SongifyAndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyOnce<AppPlugin>()
            apply { from("${rootDir}/build-logic/kotlin.gradle") }

            androidApplication()
            dynamicNamespace()
            moduleNameFix()
            configureLint()
            detekt()
            graphAssert()
            // checkstyle()
            circuit()
            metro()
            moshi()
            jetpackCompose()
            platform()

            dependencies {
                "implementation"(libs.timber)
            }

            extensions.create("songify", SongifyAndroidApplicationExtension::class.java, this)
        }
    }
}

open class SongifyAndroidApplicationExtension(private val project: Project) {

}
