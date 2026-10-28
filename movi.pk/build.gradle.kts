plugins {
    id("com.android.library")
    id("kotlin-android")
    id("com.lagradost.cloudstream3.gradle")
}

cloudstream {
    // Standard CloudStream extension configuration
    setMainClass("recloudstream.MoviPK")
}

android {
    namespace = "recloudstream.movipk"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
        targetSdk = 34
    }
}

dependencies {
    // CloudStream dependencies are automatically managed by the plugin
}
