plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.moviesdatabase"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.moviesdatabase"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            buildConfigField("String", "API_URL", "\"https://api.themoviedb.org\"")
            buildConfigField("String", "IMAGE_BASE_URL", "\"https://image.tmdb.org/t/p/w500\"")
            buildConfigField("String", "TMDB_TOKEN", "\"eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0NmFkMzRiZGU4NjM1MTVkNzE4YzYzZTY4OGMxYzE0NCIsIm5iZiI6MTcyNjcwMDg2MC4yOTgzNjUsInN1YiI6IjU3ZjkwMjZkYzNhMzY4NTA5NDAwMjM3NyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.rWqF5So6ptqs8dydFo_s6RTz_xMGZeVWcsCHL2LvApk\"")
        }
    }
    android.buildFeatures.buildConfig = true

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":common"))
    api(project(":presentation"))
    api(project(":data"))

    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation (libs.androidx.lifecycle.process)
    implementation (libs.kotlinx.coroutines.core)
    implementation(libs.constraintlayout.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation (libs.androidx.material.icons.extended)
    implementation(libs.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.gson)
    implementation (libs.androidx.datastore.preferences)

//    koin
    implementation (libs.insert.koin.koin.core)
    implementation (libs.koin.androidx.navigation)
    implementation(libs.koin.androidx.compose)
    implementation(libs.koin.android)
    implementation(libs.koin.android.compat)

    // Ktor
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.serialization)
    implementation(libs.ktor.serialization.gson)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.client.auth)
    // Logback
    implementation(libs.logback.classic)


//  testing
    debugImplementation(libs.bundles.androidx.ui.debug)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}