plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android-extensions")
    id("kotlin-android")
    kotlin("kapt")
    id("org.jlleitschuh.gradle.ktlint") version BuildPlugins.ktlint
    id("org.owasp.dependencycheck") version BuildPlugins.owasp
    id("androidx.navigation.safeargs.kotlin")
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
    sourceSets.all {
        java.srcDir("src/$name/kotlin")
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(Kotlin.coroutinesAndroid)

    implementation(SupportLibs.appCompat)
    implementation(SupportLibs.androidCoreKtx)
    implementation(SupportLibs.lifecycleViewModel)
    implementation(SupportLibs.lifecycleRuntime)
    implementation(SupportLibs.constraintLayout)
    implementation(SupportLibs.recyclerView)

    implementation(Ui.picasso)

    implementation(Navigation.navigationFragment)
    implementation(Navigation.navigationUi)

    implementation(Di.koinAndroid)
    implementation(Di.koinScope)
    implementation(Di.koinViewModel)

    implementation(Http.retrofit)
    implementation(Http.retrofitMoshiConverter)
    implementation(Http.moshi)
    implementation(Http.moshiAdapters)
    kapt(Http.kotlinCodegen)

    debugImplementation(Tools.leakCanary)
    implementation(Tools.timber)
    implementation(Tools.jodaTime)

    testImplementation(TestingLib.junit)
    testImplementation(TestingLib.archUnit)
    testImplementation(TestingLib.coroutinesTest)
    testImplementation(TestingLib.kotestRunner)
    testImplementation(TestingLib.kotestAssertions)
    testImplementation(TestingLib.kotestProperty)
    testImplementation(TestingLib.koinTest)
    testImplementation(TestingLib.mockkUnit)

    androidTestImplementation(AndroidTestingLib.androidxTestRunner)
    androidTestImplementation(AndroidTestingLib.androidxTestExtJunit)
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}
