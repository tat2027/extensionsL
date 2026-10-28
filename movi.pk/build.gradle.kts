plugins {
    id("com.android.library")
    id("kotlin-android")
    id("com.lagradost.cloudstream3.gradle")
}

cloudstream {
    // Official CloudStream master-SNAPSHOT extension properties
    set("id", "movipk")
    set("name", "MoviPK")
    set("authors", listOf("Redowan"))
    description = "MoviPK movies and series provider for CloudStream"
    set("tvTypes", listOf("Movie", "TvSeries"))
    version = 1
}

dependencies {
    // CloudStream dependencies are automatically managed by the plugin
}
