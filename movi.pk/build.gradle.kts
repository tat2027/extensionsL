plugins {
    id("com.android.library")
    id("kotlin-android")
}

cloudstream {
    name = "MoviPK"
    description = "MoviPK Extension"
    authors = listOf("YourName")
    language = "en"
    iconUrl = ""
    status = 3 // 3 means working
    tvTypes = listOf("Movie") // Movie, TvSeries, Anime, etc.
}

dependencies {
    implementation("com.github.recloudstream.cloudstream:library:master-SNAPSHOT")
}
