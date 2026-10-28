plugins {
    id("com.android.library")
    id("kotlin-android")
    id("com.lagradost.cloudstream3.gradle")
}

cloudstream {
    // Modern CloudStream properties schema
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
}

dependencies {
    // CloudStream dependencies are automatically managed by the plugin
}
