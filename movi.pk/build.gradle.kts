plugins {
    id("com.android.library")
    id("kotlin-android")
    id("com.lagradost.cloudstream3.gradle")
}

cloudstream {
    name = "MoviPK" // Yahan 'englishName' ki jagah sirf 'name' karna hai
    description = "MoviPK Extension"
    authors = listOf("MoviPK")
    language = "en"
    iconUrl = ""
    status = 3
    tvTypes = listOf("Movie")
}

dependencies {
    implementation("com.github.recloudstream.cloudstream:library:master-SNAPSHOT")
}
