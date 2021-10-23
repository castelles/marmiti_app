object Configs {
    const val appId = "castelles.com.github.androidbaseproject"
    const val compileSdkVersion = 30
    const val buildToolsVersion = "30.0.3"
    const val minSdkVersion = 22
    const val targetSdkVersion = 30
    const val versionCode = 1

    private const val release ="1"
    private const val feature = "0"
    private const val fix = "0"
    private const val hotFix = "0"
    const val versionName = "$release.$feature.$fix.$hotFix"

    const val buildType_release = "release"
    const val buildType_debug = "debug"

    const val dimensionEnv = "environment"

    // DEV
    const val dev = "dev"
    const val devAppSuffix = ".dev"
    const val devVersionNameSuffix = "-dev"

    // TEST
    const val test = "test"
    const val testAppSuffix = ".dev"
    const val testversionNameSuffix = "-dev"

    // HOMOLOG
    const val homolog = "dev"
    const val homologAppSuffix = ".dev"
    const val homologVersionNameSuffix = "-dev"
}