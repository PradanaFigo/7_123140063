import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
    id("app.cash.sqldelight") version "2.0.1"
    kotlin("plugin.serialization") version "1.9.20"
}

kotlin {
    androidTarget()

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    jvm()

    sourceSets {
        androidMain.dependencies {
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.activity.compose)
            implementation("app.cash.sqldelight:android-driver:2.0.1")
            implementation("io.insert-koin:koin-android:3.5.3")
            implementation("io.insert-koin:koin-androidx-compose:3.5.3")
            implementation("io.ktor:ktor-client-okhttp:2.3.9")
        }

        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
            implementation("org.jetbrains.compose.material:material-icons-extended:1.7.0")
            implementation("org.jetbrains.androidx.navigation:navigation-compose:2.7.0-alpha07")
            implementation("org.jetbrains.androidx.lifecycle:lifecycle-runtime-compose:2.8.0")
            implementation("org.jetbrains.androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0")
            implementation("app.cash.sqldelight:runtime:2.0.1")
            implementation("app.cash.sqldelight:coroutines-extensions:2.0.1")
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
            implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.1")
            implementation("com.russhwolf:multiplatform-settings-no-arg:1.1.1")
            implementation("com.russhwolf:multiplatform-settings-coroutines:1.1.1")
            implementation("io.insert-koin:koin-core:3.5.3")
            implementation("io.insert-koin:koin-compose:1.1.2")
            implementation("io.ktor:ktor-client-core:2.3.9")
            implementation("io.ktor:ktor-client-content-negotiation:2.3.9")
            implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.9")
        }

        // --- BAGIAN TESTING ---
        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
            implementation("app.cash.turbine:turbine:1.0.0")
            implementation("io.insert-koin:koin-test:3.5.3")
            // MockK dihapus dari sini karena ia JVM-only dan bikin error di iOS target
        }

        // Pindahkan MockK ke sini agar hanya berjalan di target Android (Fix Error 404/Not Found di iOS)
        val androidUnitTest by getting {
            dependencies {
                implementation("io.mockk:mockk:1.13.9") //
            }
        }

        iosMain.dependencies {
            implementation("app.cash.sqldelight:native-driver:2.0.1")
            implementation("io.ktor:ktor-client-darwin:2.3.9")
        }

        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutinesSwing)
            implementation("app.cash.sqldelight:sqlite-driver:2.0.1")
            implementation("io.ktor:ktor-client-okhttp:2.3.9")
        }
    }
}

sqldelight {
    databases {
        create("NotesDatabase") {
            packageName.set("com.example.project.db")
        }
    }
}

android {
    namespace = "com.example.project"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.example.project"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

// PERBAIKAN: Taruh library UI Testing di blok dependencies paling bawah (Android Scope)
dependencies {
    debugImplementation(libs.compose.uiTooling)

    // Library untuk UI Testing (Sesuai Materi Slide 23) [cite: 383]
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.6.0")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.6.0")
}

compose.desktop {
    application {
        mainClass = "com.example.project.MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.example.project"
            packageVersion = "1.0.0"
        }
    }
}