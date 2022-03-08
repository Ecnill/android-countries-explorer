import org.gradle.api.Plugin
import org.gradle.api.Project

class ConfigAndroidBuild : Plugin<Project> by PluginDelegate plugin {

    android {
        compileSdk = Config.compileSdk

        defaultConfig {
            minSdk = Config.minSdk
            targetSdk = Config.targetSdk
        }
    }

    androidApplication {
        compileSdk = Config.compileSdk

        defaultConfig {
            minSdk = Config.minSdk
            targetSdk = Config.targetSdk
        }
    }
}

