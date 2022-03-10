plugins {
    id("com.android.library")
    kotlin("android")
}

apply<ConfigAndroidBuild>()
apply<ConfigCompiler>()
apply<ConfigUnitTest>()

android {
    namespace = "ecnill.arch"
}

dependencies {
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.junit.main)
}