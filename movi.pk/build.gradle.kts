plugins {
    id("com.android.library")
    id("kotlin-android")
    id("com.lagradost.cloudstream3.gradle")
}

cloudstream {
    // Plugin ki basic details
    setID("movipk")
    setName("MoviPK")
    setAuthor("Redowan")
    description = "MoviPK movies and series provider for CloudStream"
    
    // Supported Types (Movie, TvSeries, Anime, etc.)
    setTvTypes(
        listOf(
            "Movie",
            "TvSeries"
        )
    )
    
    version = 1
}

dependencies {
    // Cloudstream dependencies gradle plugin ke zariye automatically handle ho jati hain
}
