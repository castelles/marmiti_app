plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {

    compileSdkVersion(Configs.compileSdkVersion)
    buildToolsVersion = Configs.buildToolsVersion

    defaultConfig {
        applicationId = Configs.appId
        versionCode = Configs.versionCode
        versionName = Configs.versionName
        minSdkVersion(Configs.minSdkVersion)
        targetSdkVersion(Configs.targetSdkVersion)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        dataBinding = true
    }

    buildTypes {
        getByName(Configs.buildType_release) {
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

    flavorDimensions(Configs.dimensionEnv)

    productFlavors {

        val STRING_LOWER: String = "string"
        val STRING: String = "string"
        val APP_NAME: String = "app_name"
        val APP: String = "Maniva"

        create(Configs.dev) {
            dimension = Configs.dimensionEnv
            applicationIdSuffix = Configs.devAppSuffix
            versionNameSuffix = Configs.devVersionNameSuffix

            resValue(STRING_LOWER, APP_NAME, "Maniva$versionNameSuffix")
        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation (project(":api"))

    implementation(Libraries.kotlinstdlib)
    implementation(Libraries.coreKtx)
    implementation(Libraries.appCompat)
    implementation(Libraries.materialDesign)
    implementation(Libraries.constraintLayout)
    testImplementation(Libraries.junit)
    androidTestImplementation(Libraries.junitAndroidX)
    androidTestImplementation(Libraries.espresso)

    implementation(Libraries.rxAndroid)
    implementation(Libraries.rxJava)
    implementation(Libraries.rxKotlin)

    implementation(Libraries.navigationFragment)
    implementation(Libraries.navigationUi)
    implementation(Libraries.fragment)
    implementation(Libraries.viewModelSavedState)

    implementation(Libraries.koin)
    implementation(Libraries.koinScope)
    implementation(Libraries.koinViewModel)

    implementation(Libraries.dimensSdp)
    implementation(Libraries.lottie)

    implementation(Libraries.googleAuthApi)
    implementation(Libraries.googleServices)
    implementation(platform(Libraries.firebaseBoM))
    implementation(Libraries.firebaseAnalytics)

}