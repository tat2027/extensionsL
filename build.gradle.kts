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
        classpath("com.github.recloudstream:gradle:-SNAPSHOT") // Yahan 'master-SNAPSHOT' ki jagah '-SNAPSHOT' kar diya gaya hai
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:2.3.0")
    }
}

// NOTE: allprojects wala repositories block yahan se khatam kar diya gaya hai 
// kyunki yeh ab settings.gradle.kts se handle ho raha hai.

subprojects {
    apply(plugin = "com.android.library")
    apply(plugin = "kotlin-android")
    apply(plugin = "com.lagradost.cloudstream3.gradle")

    dependencies {
        val implementation by configurations
        implementation("com.github.recloudstream.cloudstream:library:master-SNAPSHOT")
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}
