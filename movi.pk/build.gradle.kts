plugins {
    id("com.android.library")
    id("kotlin-android")
    id("com.lagradost.cloudstream3.gradle")
}

cloudstream {
    authors = listOf("Redowan")
    description = "MoviPK movies and series provider for CloudStream"
    tvTypes = listOf("Movie", "TvSeries")
    version = 1
}

android {
    namespace = "recloudstream.movipk"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    // Using the official cloudstream dependency notation that maps through local/plugin repositories
    // without triggering JitPack 401 unauthorized checks
    compileOnly("com.github.cloudstream:cloudstream3:master-SNAPSHOT")
}
