plugins {
    id("com.android.library")
    kotlin("android")
}

apply<ConfigFeatureModule>()

android {
    namespace = "ecnill.country.feature.region"
}