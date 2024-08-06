plugins {

    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("com.google.gms.google-services")
    id("androidx.navigation.safeargs.kotlin")


}

android {
    namespace = "com.mithilakshar.mithilapanchang"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mithilakshar.mithilapanchang"
        minSdk = 24
        targetSdk = 34
        versionCode=  11
        versionName = "11.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled= false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility= JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {



    implementation("com.google.android.play:app-update-ktx:2.1.0")
    implementation("com.google.android.play:review-ktx:2.0.1")

    implementation ("androidx.work:work-runtime:2.7.1")
    implementation("androidx.lifecycle:lifecycle-common-jvm:2.8.4")
    val billing_version = "7.0.0"

    implementation("com.android.billingclient:billing:$billing_version")


    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")

    
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")


    implementation("androidx.core:core-animation:1.0.0")
    implementation("androidx.compose.animation:animation-graphics-android:1.6.7")

    implementation ("com.google.firebase:firebase-firestore:24.9.1")
    implementation ("com.google.firebase:firebase-messaging:23.4.0")
    implementation("androidx.media3:media3-common:1.3.1")
    implementation("androidx.activity:activity:1.9.0")
    implementation("androidx.compose.material3:material3-android:1.2.1")


    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")


    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.google.code.gson:gson:2.8.8")


    implementation ("com.github.bumptech.glide:glide:4.16.0")

    implementation ("com.squareup.okhttp3:okhttp:4.9.1")

    implementation ("com.squareup.okio:okio:3.0.0")


    implementation("com.google.android.play:app-update:2.1.0")
    implementation("com.google.android.play:app-update-ktx:2.1.0")

    implementation ("com.airbnb.android:lottie:6.4.0")

    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("com.google.android.material:material:1.10.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation(platform("com.google.firebase:firebase-bom:33.1.0"))
    implementation("com.google.firebase:firebase-storage")

    implementation("androidx.core:core-ktx:1.13.1")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    implementation ("com.squareup.picasso:picasso:2.8")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("com.github.denzcoskun:ImageSlideshow:0.1.2")
}