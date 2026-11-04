plugins {
    id("com.android.library")
    id("kotlin-android")
    id("com.lagradost.cloudstream3.gradle")
}

cloudstream {
    // Cloudstream extension ke liye yeh properties valid hain:
    authors = listOf("MoviPK")
    description = "MoviPK Extension"
    language = "en"
    iconUrl = ""
    status = 3
    tvTypes = listOf("Movie")
    // Note: Extension ka display name automatically project/folder name ya plugin settings se utha liya jata hai, 
    // is liye yahan extra 'id' ya 'name' assign karne ki zaroorat nahi hai.
}

dependencies {
    implementation("com.github.recloudstream.cloudstream:library:master-SNAPSHOT")
}
