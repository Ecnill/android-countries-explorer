plugins {
    id("com.android.library")
    kotlin("android")
}

apply<ConfigFeatureModule>()

android {
    namespace = "ecnill.country.feature.country"
}

dependencies {
    implementation(project(":arch"))
    implementation(project(":network"))

    implementation(libs.accompanist.swiperefresh)
}