plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("com.google.dagger.hilt.android") // Hilt Plugin
    id("kotlin-kapt") // Required for Hilt
}

android {
    namespace = "kd.dhyani.newsapp"
    compileSdk = 34
    buildFeatures.buildConfig = true

    defaultConfig {
        applicationId = "kd.dhyani.newsapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
        buildConfigField("String", "API_KEY", "\"47e8fe9278ff42e1bf519e6f247a2d53\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }

    packaging {
        resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")

    // ✅ Jetpack Compose BOM (Ensures all Compose dependencies are version-compatible)
    implementation(platform("androidx.compose:compose-bom:2024.02.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    // ✅ DataStore (Stable Version)
    implementation("androidx.datastore:datastore-preferences:1.1.0")

    // ✅ Hilt for Dependency Injection
    implementation("com.google.dagger:hilt-android:2.50")
    kapt("com.google.dagger:hilt-android-compiler:2.50")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0") // Hilt for Jetpack Compose

    // ✅ Room Database
    implementation("androidx.room:room-common:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")

    // ✅ Testing Dependencies
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.02.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    // ✅ Debugging
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // ✅ Splash Screen API (Fixed)
    implementation("androidx.core:core-splashscreen:1.0.1")

    implementation("com.google.accompanist:accompanist-systemuicontroller:0.32.0")

    // Retrofit & Gson
    implementation("com.squareup.retrofit2:retrofit:2.9.0") // Retrofit core
    implementation("com.squareup.retrofit2:converter-gson:2.9.0") // JSON converter

    // Paging 3 for Jetpack Compose
    implementation("androidx.paging:paging-runtime:3.2.1")
    implementation("androidx.paging:paging-compose:3.2.1")

    // Coil for image loading
    implementation("io.coil-kt:coil-compose:2.5.0")
    implementation("androidx.compose.material:material-icons-extended:1.5.4")

    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.8.10")
    implementation ("com.google.accompanist:accompanist-swiperefresh:0.31.3-beta")

    implementation ("com.google.accompanist:accompanist-swiperefresh:0.31.3-beta")

}
