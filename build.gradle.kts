import com.lagradost.cloudstream3.gradle.CloudstreamExtension
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io") {
            credentials {
                username = System.getenv("JITPACK_TOKEN") ?: ""
            }
        }
    }

    dependencies {
        classpath("com.android.tools.build:gradle:8.7.3")
        // master-SNAPSHOT ki jagah main-SNAPSHOT use kiya gaya hai
        classpath("com.github.recloudstream:gradle:main-SNAPSHOT")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:2.3.0")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io") {
            credentials {
                username = System.getenv("JITPACK_TOKEN") ?: ""
            }
        }
    }
}

subprojects {
    apply(plugin = "com.android.library")
    apply(plugin = "kotlin-android")
    apply(plugin = "com.lagradost.cloudstream3.gradle")

    dependencies {
        val implementation by configurations
        implementation("com.github.recloudstream.cloudstream:library:main-SNAPSHOT")
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}
