plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
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

        buildConfigField("String", "USER_TOKEN", "\"2F4zAdm1e1Nx1nB2jGrzPcq6OplMpT1WxE3i\"")
        buildConfigField("String", "API_URL", "\"http://192.168.0.107/seminar-proposal-exam/public/\"")
//        buildConfigField("String", "API_URL", "\"http://10.21.2.108/seminar-proposal-exam/public/\"")
//
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
}

dependencies {

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Lifecycle and preference
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation ("androidx.datastore:datastore-preferences:1.0.0")

    // KTX
    implementation ("androidx.activity:activity-ktx:1.8.2")

    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.11.0")

    //CameraX
//    def camerax_version = "1.2.2"
//    implementation ("androidx.camera:camera-camera2:${camerax_version}"
//    implementation ("androidx.camera:camera-lifecycle:${camerax_version}"
//    implementation ("androidx.camera:camera-view:${camerax_version}"

    // Glide
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    implementation ("de.hdodenhof:circleimageview:3.1.0")

    // Navigation
    val nav_version = "2.7.6"
    implementation ("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation ("androidx.navigation:navigation-ui-ktx:$nav_version")
    implementation ("androidx.navigation:navigation-dynamic-features-fragment:$nav_version")

    implementation ("androidx.viewpager2:viewpager2:1.0.0")

    // Paging
    implementation ("androidx.paging:paging-runtime-ktx:3.2.1")

    // Cropper
    implementation ("com.vanniktech:android-image-cropper:4.3.3")

    implementation("androidx.cardview:cardview:1.0.0")
}