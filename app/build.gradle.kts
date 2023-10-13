plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.appfrisaahorasi"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.appfrisaahorasi"
        minSdk = 24
        compileSdkPreview = "UpsideDownCake"
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.10.00"))


    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.foundation:foundation-android:1.5.3")
    //implementation("com.google.android.libraries.maps:maps:3.1.0-beta")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.10.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")


    implementation("androidx.compose.ui:ui:1.5.3")
    implementation("androidx.compose.material:material:1.5.3")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0-alpha07")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0-alpha02")
    implementation ("androidx.compose.material:material-icons-extended:1.5.3")
    implementation ("com.google.maps.android:android-maps-utils:3.5.3")
    implementation ("com.google.maps.android:maps-utils-ktx:4.0.0")

    implementation("io.coil-kt:coil-compose:2.4.0")
    // Import the BoM for the Firebase platform
    implementation(platform("com.google.firebase:firebase-bom:32.3.1"))

    // Add the dependency for the Firebase Authentication library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-auth-ktx")

    // firestore
    implementation("com.google.firebase:firebase-firestore:24.8.1") // Replace with the latest version

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.7.3") // or a suitable version

    implementation("androidx.navigation:navigation-compose:2.7.4")


    //ESTAS LIBRERIAS CAUSAN ERROR >:( VVVVV
    //implementation("com.google.android.libraries.maps:maps:3.1.0-beta")
    //implementation("com.google.maps.android:maps-v3-ktx:3.4.0")
    //^^^^^^^^^^^^^^^^
    implementation("androidx.fragment:fragment:1.6.1")

    implementation ("com.google.maps.android:maps-compose:3.1.0")
    implementation ("com.google.android.gms:play-services-maps:18.1.0")


    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.7.3") // for the await function

}