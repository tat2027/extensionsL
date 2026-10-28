plugins {
    id("com.android.library")
    id("kotlin-android")
    id("com.lagradost.cloudstream3.gradle")
}

cloudstream {
    setID("movipk")
    setName("MoviPK")
    setAuthor("Redowan")
    description = "MoviPK movies and series provider for CloudStream"
    setTvTypes(
        listOf(
            "Movie",
            "TvSeries"
        )
    )
    version = 1
}

dependencies {
    // CloudStream dependencies are automatically managed by the plugin
}
