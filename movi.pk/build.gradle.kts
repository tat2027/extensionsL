plugins {
    id("com.android.library")
    id("kotlin-android")
    id("com.lagradost.cloudstream3.gradle")
}

// Modern CloudStream DSL syntax (yeh getters/setters ki bajaye direct property assignment use karta hai taake koi Unresolved reference error na aaye)
cloudstream {
    id = "movipk"
    name = "MoviPK"
    author = "Redowan"
    description = "MoviPK movies and series provider for CloudStream"
    tvTypes = listOf("Movie", "TvSeries")
    version = 1
}

dependencies {
    // CloudStream dependencies are automatically managed by the plugin
}
