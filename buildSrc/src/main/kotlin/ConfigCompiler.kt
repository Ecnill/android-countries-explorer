import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class ConfigCompiler : Plugin<Project> by PluginDelegate plugin {

    val targetJvmVersion = JavaVersion.VERSION_11

    android {
        compileOptions {
            sourceCompatibility = targetJvmVersion
            targetCompatibility = targetJvmVersion
        }
    }

    androidApplication {
        compileOptions {
            sourceCompatibility = targetJvmVersion
            targetCompatibility = targetJvmVersion
        }
    }
}