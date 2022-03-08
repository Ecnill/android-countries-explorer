plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

apply<ConfigAndroidBuild>()
apply<ConfigCompiler>()

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
    implementation(libs.androidx.appcompat)
}