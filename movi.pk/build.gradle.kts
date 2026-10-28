plugins {
    id("com.android.library")
    id("kotlin-android")
}

apply(plugin = "com.lagradost.cloudstream3.gradle")

com.lagradost.cloudstream3.gradle.CloudstreamExtension().apply {
    setID("movipk")
    setName("MoviPK")
    setAuthor("Redowan")
    description = "MoviPK movies and series provider for CloudStream"
    setTvTypes(listOf("Movie", "TvSeries"))
    version = 1
}
