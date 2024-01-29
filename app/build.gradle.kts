plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.navigation)
}

android {
    namespace = "com.tashuseyin.kukacase"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.tashuseyin.kukacase"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            buildConfigField("String", "BASE_PRODUCTS_URL", "\"https://fakestoreapi.com/\"")
            buildConfigField("String", "BASE_OUTFITS_URL", "\"https://my-json-server.typicode.com/\"")
        }

        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.fragment)

    //lifecycle
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.lifecycle.viewmodel)

    //hilt
    implementation(libs.hilt)
    implementation(libs.androidx.swiperefreshlayout)
    kapt(libs.hilt.compiler)

    //retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson.converter)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    //material
    implementation(libs.material)
    implementation(libs.constraintlayout)

    //navigation
    implementation(libs.navigation.ui)
    implementation(libs.navigation.fragment)

    //coil
    implementation(libs.coil)

    //coroutines
    implementation(libs.coroutines)

    //splashscreen
    implementation(libs.splash.screen)
}