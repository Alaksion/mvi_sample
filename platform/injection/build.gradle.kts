plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {

    namespace = "com.example.mvisample"

    compileSdk = BuildConstants.COMPILE_SDK

    defaultConfig {
        minSdk = BuildConstants.MIN_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            setProguardFiles(listOf(getDefaultProguardFile("proguard-android-optimize.txt")))
        }
    }
    compileOptions {
        sourceCompatibility = BuildConstants.JAVA_VERSION
        targetCompatibility = BuildConstants.JAVA_VERSION
    }
    kotlinOptions {
        jvmTarget = BuildConstants.JVM_TARGET
    }
}

dependencies {

    implementation(libs.androidX.core.ktx)

    implementation(libs.coroutines.core)
    testImplementation(libs.coroutines.test)

    implementation(libs.hilt.core)
    kapt(libs.hilt.compiler)

    testImplementation(libs.junit)
    testImplementation(libs.truth)
}

kapt {
    correctErrorTypes = true
}