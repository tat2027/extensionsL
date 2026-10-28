plugins {
    id("com.android.library")
    id("kotlin-android")
    id("com.lagradost.cloudstream3.gradle")
}

cloudstream {
    id = "movipk"
    pluginName = "MoviPK"
    author = "Redowan"
    description = "MoviPK movies and series provider for CloudStream"
    tvTypes = listOf("Movie", "TvSeries")
    version = 1
}

dependencies {
    // CloudStream dependencies are automatically managed by the plugin
}
