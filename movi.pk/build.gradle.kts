plugins {
    id("com.android.library")
    id("kotlin-android")
    id("com.lagradost.cloudstream3.gradle")
}

cloudstream {
    // Correct method calls for CloudstreamExtension DSL
    setID("movipk")
    setName("MoviPK")
    setAuthors(listOf("Redowan"))
    description = "MoviPK movies and series provider for CloudStream"
    setTvTypes(listOf("Movie", "TvSeries"))
    setVersion(1)
}

dependencies {
    // CloudStream dependencies are automatically managed by the plugin
}
