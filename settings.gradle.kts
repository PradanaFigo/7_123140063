rootProject.name = "ProfileApp"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google() // Dibuka aksesnya secara penuh
        mavenCentral()
        gradlePluginPortal()
        // Tambahan buat library Compose Multiplatform
        maven("https://maven.pkg.jetbrains.space/public/p/compose/patch")
    }
}

dependencyResolutionManagement {
    repositories {
        google() // Dibuka aksesnya secara penuh agar ui-test-junit4 ketemu
        mavenCentral()
        // Tambahan buat library Compose Multiplatform
        maven("https://maven.pkg.jetbrains.space/public/p/compose/patch")
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

include(":composeApp")