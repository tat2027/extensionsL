plugins {
    id("com.lagradost.cloudstream3.gradle")
}

version = 1

cloudstream {
    description = "Watch M4uHd Free movies and shows in Cloudstream"
    authors = listOf("KaifTaufiq")
    status = 1
    tvTypes = listOf("Movie", "TvSeries")
    isCrossPlatform = true
}

dependencies {
    // Cloudstream core dependency agar automatic fetch na ho
    implementation(libs.cloudstream3) // ya agar version catalog use nahi ho raha toh standard dependency
}
