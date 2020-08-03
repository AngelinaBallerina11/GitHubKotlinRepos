object Android {
    const val applicationId = "cz.angelina.kotlingithub"

    const val versionCode = 1
    const val versionName = "1"
}

object Sdk {
    const val minSdkVersion = 21
    const val targetSdkVersion = 30
    const val compileSdkVersion = 30
}

object Versions {
    const val appCompat = "1.1.0"
    const val constraintLayout = "1.1.3"
    const val recyclerView = "1.1.0"
    const val coreKtx = "1.2.0"
    const val lifecycleViewModel = "2.2.0"
    const val lifecycleRuntime = "2.2.0"
    const val koin = "2.1.6"
    const val leakCanary = "2.4"
    const val timber = "4.7.1"
    const val retrofit = "2.9.0"
    const val moshi = "1.9.3"
    const val navigationComponent = "2.3.0"
    const val coroutines = "1.3.8"
    const val picasso = "2.5.2"
    const val joda = "2.10.6"

    object Test {
        const val androidxTestExt = "1.1.1"
        const val androidxTest = "1.2.0"
        const val junit = "5.6.2"
        const val espressoCore = "3.2.0"
        const val archUnit = "0.14.1"
        const val coroutines = "1.3.8"
        const val kotest = "4.1.3"
        const val mockk = "1.10.0"
    }
}

object BuildPlugins {
    const val androidGradlePlugin = "4.0.1"
    const val kotlin = "1.3.72"
    const val ktlint = "9.3.0"
    const val owasp = "5.3.2.1"
}

object SupportLibs {
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val constraintLayout = "com.android.support.constraint:constraint-layout:${Versions.constraintLayout}"
    const val androidCoreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleViewModel}"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntime}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
}

object Kotlin {
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
}

object Ui {
    const val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"
}

object Navigation {
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigationComponent}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigationComponent}"
}

object Di {
    const val koinAndroid = "org.koin:koin-android:${Versions.koin}"
    const val koinScope = "org.koin:koin-androidx-scope:${Versions.koin}"
    const val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
}

object Http {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitMoshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
    const val moshiAdapters = "com.squareup.moshi:moshi-adapters:${Versions.moshi}"
    const val kotlinCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
}

object Tools {
    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val jodaTime = "joda-time:joda-time:${Versions.joda}"
}

object TestingLib {
    const val junit = "org.junit.jupiter:junit-jupiter:${Versions.Test.junit}"
    const val archUnit = "com.tngtech.archunit:archunit-junit5:${Versions.Test.archUnit}"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.Test.coroutines}"
    const val kotestRunner = "io.kotest:kotest-runner-junit5-jvm:${Versions.Test.kotest}"
    const val kotestAssertions = "io.kotest:kotest-assertions-core-jvm:${Versions.Test.kotest}"
    const val kotestProperty = "io.kotest:kotest-property-jvm:${Versions.Test.kotest}"
    const val koinTest = "org.koin:koin-test:${Versions.koin}"
    const val mockkUnit = "io.mockk:mockk:${Versions.Test.mockk}"
}

object AndroidTestingLib {
    const val androidxTestRules = "androidx.test:rules:${Versions.Test.androidxTest}"
    const val androidxTestRunner = "androidx.test:runner:${Versions.Test.androidxTest}"
    const val androidxTestExtJunit = "androidx.test.ext:junit:${Versions.Test.androidxTestExt}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.Test.espressoCore}"
}