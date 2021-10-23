plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    compileSdkVersion(Configs.compileSdkVersion)
    buildToolsVersion = Configs.buildToolsVersion

    defaultConfig {
        versionCode = Configs.versionCode
        versionName = Configs.versionName
        minSdkVersion(Configs.minSdkVersion)
        targetSdkVersion(Configs.targetSdkVersion)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

        val STRING = "String"
        val BASE_URL = "BASE_URL"

        create(Configs.dev) {
            dimension = Configs.dimensionEnv

            buildConfigField(STRING, BASE_URL, "\"https://api.github.com/\"")
        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(Libraries.kotlinstdlib)
    implementation(Libraries.coreKtx)
    implementation(Libraries.appCompat)
    implementation(Libraries.materialDesign)
    implementation(Libraries.constraintLayout)
    testImplementation(Libraries.junit)
    androidTestImplementation(Libraries.junitAndroidX)
    androidTestImplementation(Libraries.espresso)

    // API
    implementation(Libraries.retrofit)
    implementation(Libraries.gsonConverter)
    implementation(Libraries.moshiConverter)
    implementation(Libraries.gson)
    implementation(Libraries.loggingInterceptor)
    implementation(Libraries.retrofit)
    implementation(Libraries.okHttp)

    implementation(Libraries.rxAndroid)
    implementation(Libraries.rxJava)
    implementation(Libraries.rxKotlin)
    implementation(Libraries.coroutinesFlow)

}