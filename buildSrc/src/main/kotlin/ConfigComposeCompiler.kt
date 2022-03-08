import org.gradle.api.Plugin
import org.gradle.api.Project

class ConfigComposeCompiler : Plugin<Project> by PluginDelegate plugin {
    android {
        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = libs.findVersion("compose").get().requiredVersion
        }
    }
    androidApplication {
        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = libs.findVersion("compose").get().requiredVersion
        }
    }
}