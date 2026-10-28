plugins {
    id("com.android.library")
    id("kotlin-android")
    id("com.lagradost.cloudstream3.gradle")
}

extensions.configure<com.lagradost.cloudstream3.gradle.CloudstreamExtension> {
    setID("movipk")
    setName("MoviPK")
    setAuthor("Redowan")
    description = "MoviPK movies and series provider for CloudStream"
    setTvTypes(listOf("Movie", "TvSeries"))
    version = 1
}

dependencies {
    // CloudStream dependencies are automatically handled by the plugin
}
