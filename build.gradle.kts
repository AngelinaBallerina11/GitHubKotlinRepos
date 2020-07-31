buildscript {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${BuildPlugins.androidGradlePlugin}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${BuildPlugins.kotlin}")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
}

tasks.register("clean").configure {
    delete(rootProject.buildDir)
}