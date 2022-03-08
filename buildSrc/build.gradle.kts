plugins {
    `kotlin-dsl`
}

kotlinDslPluginOptions {
    jvmTarget.set(JavaVersion.VERSION_11.toString())
}

repositories {
    gradlePluginPortal()
    google()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
    implementation("com.android.tools.build:gradle:7.1.2")
    implementation("org.openapitools:openapi-generator-gradle-plugin:5.2.1")
}