plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

apply<ConfigFeatureModule>()

android {
    namespace = "ecnill.country.feature.country"
}

dependencies {
    implementation(project(":arch"))
    implementation(project(":network"))

    implementation(libs.accompanist.swiperefresh)

    implementation(libs.room.main)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)
}