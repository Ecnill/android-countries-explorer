import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class ConfigFeatureModule : Plugin<Project> by PluginDelegate plugin {
    apply<ConfigAndroidBuild>()
    apply<ConfigCompiler>()
    apply<ConfigComposeCompiler>()

    dependencies {
        "implementation"(libs.dependency("koin.android"))

        "implementation"(project(":design"))
        "implementation"(libs.dependency("androidx.navigation.compose"))
        "implementation"(libs.dependency("koin.compose"))

        "implementation"(libs.dependency("timber"))
    }
}