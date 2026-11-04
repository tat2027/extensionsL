plugins {
    id("com.android.library")
    id("kotlin-android")
}

cloudstream {
    setEnglishName("MoviPK")
    description = "MoviPK Extension"
    authors = arrayListOf("YourName")
    language = "en"
    iconUrl = ""
    status = 3 // 3 means working
    tvTypes = arrayListOf(0) // 0 means Movie/TV
}

dependencies {
    // pre-release ki jagah library dependency ko standard rakhein
    implementation("com.github.recloudstream.cloudstream:library:master-SNAPSHOT")
}
