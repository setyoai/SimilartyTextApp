plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jebrains.kotlin)
    id ("androidx.navigation.safeargs")
}

android {
    namespace = "com.setyo.similartytextapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.setyo.similartytextapp"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "API_URL", "\"http://192.168.0.109/seminar-proposal-exam/public/\"")
//        buildConfigField("String", "API_URL", "\"192.168.175.44/seminar-proposal-exam/public/\"")
//        buildConfigField("String", "API_URL", "\"http://10.21.4.100/seminar-proposal-exam/public/\"")
//
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // Testing dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Compose
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.runtime.livedata)
    implementation(libs.androidx.compose.ui.tooling)


    // Lifecycle and preference
    implementation (libs.androidx.lifecycle.livedata.ktx)
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    implementation (libs.androidx.datastore.preferences)

    // KTX
    implementation (libs.androidx.activity.ktx)

    // Retrofit
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.logging.interceptor)

    //CameraX
//    def camerax_version = "1.2.2"
//    implementation ("androidx.camera:camera-camera2:${camerax_version}"
//    implementation ("androidx.camera:camera-lifecycle:${camerax_version}"
//    implementation ("androidx.camera:camera-view:${camerax_version}"

    // Glide
    implementation (libs.glide)
    implementation (libs.hdodenhof.circleimageview)

    // Navigation
    implementation (libs.androidx.navigation.fragment.ktx)
    implementation (libs.androidx.navigation.ui.ktx)
    implementation (libs.androidx.navigation.dynamic.features.fragment)

    implementation (libs.androidx.viewpager2)

    // Paging
    implementation (libs.androidx.paging.runtime.ktx)

    // Cropper
    implementation (libs.android.image.cropper)

    // CardView
    implementation(libs.androidx.cardview)
}