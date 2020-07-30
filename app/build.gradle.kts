plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
    id("kotlin-android-extensions")
    id("kotlin-android")
    id("org.jlleitschuh.gradle.ktlint") version BuildPlugins.ktlint
    id("org.owasp.dependencycheck") version BuildPlugins.owasp
}

android {
    compileSdkVersion(29)

    defaultConfig {
        applicationId = Android.applicationId

        minSdkVersion(Sdk.minSdkVersion)
        targetSdkVersion(Sdk.targetSdkVersion)

        versionCode = Android.versionCode
        versionName = Android.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            isMinifyEnabled = false
            isDebuggable = true
        }
    }
    lintOptions {
        lintConfig = File("lint.xml")
        isWarningsAsErrors = true
        isAbortOnError = true
    }
    sourceSets.all {
        java.srcDir("src/$name/kotlin")
    }
    buildToolsVersion = "30.0.0"
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation(SupportLibs.appCompat)
    implementation(SupportLibs.androidCoreKtx)
    implementation(SupportLibs.lifecycle)
    implementation(SupportLibs.constraintLayout)
    implementation(SupportLibs.recyclerView)

    implementation(Di.koinAndroid)
    implementation(Di.koinScope)
    implementation(Di.koinViewModel)

    debugImplementation(Tools.leakCanary)
    implementation(Tools.timber)

    testImplementation(TestingLib.junit)
    testImplementation(TestingLib.archUnit)

    androidTestImplementation(AndroidTestingLib.androidxTestRunner)
    androidTestImplementation(AndroidTestingLib.androidxTestExtJunit)
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}
