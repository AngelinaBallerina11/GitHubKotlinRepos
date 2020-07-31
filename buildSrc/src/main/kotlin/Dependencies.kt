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
    const val lifecycle = "2.2.0"
    const val koin = "2.1.6"
    const val leakCanary = "2.4"
    const val timber = "4.7.1"

    object Test {
        const val androidxTestExt = "1.1.1"
        const val androidxTest = "1.2.0"
        const val junit = "5.6.2"
        const val espressoCore = "3.2.0"
        const val archUnit = "0.14.1"
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
    const val lifecycle = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
}

object Di {
    const val koinAndroid = "org.koin:koin-android:${Versions.koin}"
    const val koinScope = "org.koin:koin-androidx-scope:${Versions.koin}"
    const val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
}

object Tools {
    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
}

object TestingLib {
    const val junit = "org.junit.jupiter:junit-jupiter:${Versions.Test.junit}"
    const val archUnit = "com.tngtech.archunit:archunit-junit5:${Versions.Test.archUnit}"
}

object AndroidTestingLib {
    const val androidxTestRules = "androidx.test:rules:${Versions.Test.androidxTest}"
    const val androidxTestRunner = "androidx.test:runner:${Versions.Test.androidxTest}"
    const val androidxTestExtJunit = "androidx.test.ext:junit:${Versions.Test.androidxTestExt}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.Test.espressoCore}"
}