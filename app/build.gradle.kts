plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.sistema_ventas"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.sistema_ventas"
        minSdk = 24
        targetSdk = 36
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
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    //nuvas depedencias
    implementation("androidx.navigation:navigation-fragment:2.8.2")
    implementation("androidx.navigation:navigation-ui:2.8.2")
    implementation("com.google.android.material:material:1.12.0")
}