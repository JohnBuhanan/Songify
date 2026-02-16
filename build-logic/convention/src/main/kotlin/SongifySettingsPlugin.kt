import com.songify.extension.buildCacheConfig
import com.songify.extension.spotlight
import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings

class SongifySettingsPlugin : Plugin<Settings> {
    override fun apply(settings: Settings) {
        with(settings) {
            spotlight()
            buildCacheConfig()

            enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

            rootProject.name = "Songify"
        }
    }
}
