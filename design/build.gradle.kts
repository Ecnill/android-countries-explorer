plugins {
    id("com.android.library")
    kotlin("android")
}

apply<ConfigAndroidBuild>()
apply<ConfigCompiler>()
apply<ConfigComposeCompiler>()

android {
    namespace = "ecnill.design"
}

dependencies {
    api(libs.compose.foundation)
    api(libs.compose.material)
    api(libs.compose.ui.main)
    api(libs.compose.ui.tooling)
    implementation(libs.accompanist.systemUiController)
    implementation(libs.coil.compose)
}