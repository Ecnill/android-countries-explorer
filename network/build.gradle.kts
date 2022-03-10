plugins {
    id("com.android.library")
    kotlin("android")
}

apply<ConfigAndroidBuild>()
apply<ConfigCompiler>()

android {
    namespace = "ecnill.country.network"
}

dependencies {
    implementation(libs.koin.android)

    implementation(libs.retrofit.main)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.moshi.main)
    implementation(libs.moshi.kotlin)

    implementation(libs.timber)
}