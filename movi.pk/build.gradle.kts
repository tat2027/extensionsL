plugins {
    id("com.android.library")
    id("kotlin-android")
}

cloudstream {
    // Properties ko set karne ke liye yeh standard format use karein
    setEnglishName("MoviPK")
    description = "MoviPK Extension"
    authors = listOf("YourName")
    language = "en"
    iconUrl = ""
    status = 3 // 3 means working
    tvTypes = listOf("Movie")
}

dependencies {
    implementation("com.github.recloudstream.cloudstream:library:master-SNAPSHOT")
}
