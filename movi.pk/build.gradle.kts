plugins {
    id("com.android.library")
    id("kotlin-android")
    id("com.lagradost.cloudstream3.gradle") // Yeh plugin lazmi hona chahiye
}

cloudstream {
    englishName = "MoviPK"
    description = "MoviPK Extension"
    authors = listOf("MoviPK")
    language = "en"
    iconUrl = ""
    status = 3 // 3 means working
    tvTypes = listOf("Movie")
}

dependencies {
    implementation("com.github.recloudstream.cloudstream:library:master-SNAPSHOT")
}
