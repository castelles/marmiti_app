// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
val kotlin_version by extra("1.5.10")
    //    val kotlin_version by extra("1.5.10")

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath (Libraries.buildGradle)
        classpath (Libraries.kotlinGradle)
        classpath (Libraries.navigationPlugin)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts.kts files
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
}

tasks.register("clean", Delete::class){
    delete(rootProject.buildDir)
}