plugins {
    id("com.github.recloudstream.gradle")
}

cloudstream {
    description = "Watch M4uHd Free movies and shows in Cloudstream"
    authors = listOf("KaifTaufiq")
    status = 1
    tvTypes = listOf("Movie", "TvSeries")
    isCrossPlatform = true
}
