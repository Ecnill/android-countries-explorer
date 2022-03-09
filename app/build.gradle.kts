plugins {
    id("com.android.application")
    kotlin("android")
}

apply<ConfigAndroidBuild>()
apply<ConfigCompiler>()
apply<ConfigComposeCompiler>()

android {
    defaultConfig {
        applicationId = "com.ecnill.country"
        versionCode = Config.versionCode
        versionName = Config.versionName
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
        }
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(project(":feature:launch"))
    implementation(project(":feature:region"))

    implementation(libs.koin.android)

    implementation(libs.androidx.appcompat)
    implementation(libs.android.material)
    implementation(libs.androidx.navigation.compose)

    implementation(libs.timber)
}