import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType

class ConfigUnitTest : Plugin<Project> by PluginDelegate plugin {

    tasks.withType<Test> {
        minHeapSize = "256m"
        maxHeapSize = "512m"
    }

    dependencies {
        "api"(libs.dependency("coroutines.test"))
        "testImplementation"(libs.dependency("kotest.assertions"))
        "testImplementation"(libs.dependency("mockk"))
        "testImplementation"(libs.dependency("junit.main"))
    }
}