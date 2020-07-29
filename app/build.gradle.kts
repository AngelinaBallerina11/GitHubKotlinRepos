plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
    id("kotlin-android-extensions")
    id("kotlin-android")
    id("org.jlleitschuh.gradle.ktlint") version BuildPlugins.ktlint
}

android {
    compileSdkVersion(Sdk.compileSdkVersion)

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
    sourceSets["main"].java.srcDir("src/main/kotlin")
    sourceSets["test"].java.srcDir("src/test/kotlin")
}

dependencies {
    implementation(kotlin("stdlib-jdk7"))

    implementation(SupportLibs.appCompat)
    implementation(SupportLibs.androidCoreKtx)
    implementation(SupportLibs.lifecycle)
    implementation(SupportLibs.constraintLayout)

    implementation(Di.koinAndroid)
    implementation(Di.koinScope)
    implementation(Di.koinViewModel)

    debugImplementation(Tools.leakCanary)

    testImplementation(TestingLib.junit)
    androidTestImplementation(AndroidTestingLib.androidxTestRunner)
    androidTestImplementation(AndroidTestingLib.androidxTestExtJunit)
}
