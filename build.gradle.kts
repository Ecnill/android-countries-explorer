import io.gitlab.arturbosch.detekt.Detekt

buildscript {
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.30")
    }
}
@Suppress("UnstableApiUsage", "DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.detekt)
}

//region Detekt
dependencies {
    detektPlugins(libs.detekt.ktLint)
}

detekt {
    source = files("$projectDir")
    config = files("$projectDir/detekt.yml")
    parallel = true
}

tasks.withType<Detekt> {
    exclude("**/build/**")
    exclude("**/buildSrc/**")
    exclude("**/*gradle.kts")
}
//endregion

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}