import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.findByType
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension

internal object PluginDelegate

internal infix fun PluginDelegate.plugin(config: Project.() -> Unit) = Plugin<Project> { config(it) }

internal fun Project.kotlin(block: KotlinProjectExtension.() -> Unit) {
    extensions.findByType<KotlinProjectExtension>()?.apply(block)
}

internal fun Project.android(block: LibraryExtension.() -> Unit) {
    extensions.findByType<LibraryExtension>()?.apply(block)
}

internal fun Project.androidApplication(block: ApplicationExtension.() -> Unit) {
    extensions.findByType<ApplicationExtension>()?.apply(block)
}