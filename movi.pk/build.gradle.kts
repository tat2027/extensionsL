plugins {
    id("com.android.library")
    id("kotlin-android")
    id("com.lagradost.cloudstream3.gradle")
}

configure<com.lagradost.cloudstream3.gradle.CloudstreamExtension> {
    // Gradle properties ko .set() ke sath define karna padega kyunki yeh Property<T> hain
    this.id = "movipk"
    this.name = "MoviPK"
    this.authors = listOf("Redowan")
    this.description = "MoviPK movies and series provider for CloudStream"
    this.tvTypes = listOf("Movie", "TvSeries")
    this.version = 1
}

dependencies {
    // CloudStream dependencies are automatically managed by the plugin
}
