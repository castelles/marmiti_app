object Versions {
    const val flowJvmVersion = "1.4.1"
    const val buildGradleVersion = "4.2.2"
    const val kotlinVersion = "1.5.10"
    const val coreKtxVersion = "1.6.0"
    const val appcompatVersion = "1.3.1"
    const val materialVersion = "1.4.0"
    const val constraintLayoutVersion = "2.0.4"
    const val junitVersion = "4.+"
    const val junitAndroidXVersion = "1.1.3"
    const val lottieVersion = "4.2.0"
    const val espressoVersion = "3.4.0"
    const val navigationVersion = "2.3.3"
    const val navigationKtxVersion = "2.3.5"
    const val koinVersion = "2.2.0"
    const val dimesLayoutVersion = "1.0.5"
    const val okhttpVersion = "2.7.2"
    const val retrofitVersion = "2.9.0"
    const val rxJavaVersion = "2.2.9"
    const val rxKotlinVersion = "2.1.0"
    const val rxVersion = "1.2.1"
    const val gsonVersion = "2.8.6"
    const val loggingVersion = "4.7.2"
    const val fragmentVersion = "1.3.+"
    const val viewModelSavedStateVersion = "2.3.0"
}

object Libraries {
    const val buildGradle = "com.android.tools.build:gradle:${Versions.buildGradleVersion}"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
    const val kotlinstdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtxVersion}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appcompatVersion}"
    const val materialDesign = "com.google.android.material:material:${Versions.materialVersion}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    const val junit = "junit:junit:${Versions.junitVersion}"
    const val junitAndroidX = "androidx.test.ext:junit:${Versions.junitAndroidXVersion}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoVersion}"
    const val navigationPlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigationVersion}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
    const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofitVersion}"
//    const val scalarsConverter = "com.squareup.retrofit2:converter-scalars:${Versions}"
    const val gson = "com.google.code.gson:gson:${Versions.gsonVersion}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.loggingVersion}"

    // OKHTTP
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okhttpVersion}"

    // RX
    const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJavaVersion}"
    const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlinVersion}"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxKotlinVersion}"
//    const val rx = "io.reactivex:rxandroid:${Versions.rxVersion}"

    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigationKtxVersion}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigationKtxVersion}"

    const val fragment = "androidx.fragment:fragment-ktx:${Versions.fragmentVersion}"
    const val viewModelSavedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.viewModelSavedStateVersion}"

    // KOIN
    const val koin = "org.koin:koin-android:${Versions.koinVersion}"
    const val koinScope = "org.koin:koin-android-scope:${Versions.koinVersion}"
    const val koinViewModel= "org.koin:koin-android-viewmodel:${Versions.koinVersion}"
    const val lottie = "com.airbnb.android:lottie:${Versions.lottieVersion}"

    const val dimensSdp = "com.intuit.sdp:sdp-android:${Versions.dimesLayoutVersion}"
    const val coroutinesFlow = "org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:${Versions.flowJvmVersion}"
}