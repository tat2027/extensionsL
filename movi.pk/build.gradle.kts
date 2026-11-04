plugins {
    id("com.android.library")
    id("kotlin-android")
    id("com.lagradost.cloudstream3.gradle")
}

android {
    compileSdk = 34

    defaultConfig {
        minSdk = 21
        targetSdk = 34
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

cloudstream {
    authors = listOf("MoviPK")
    description = "MoviPK Extension"
    language = "en"
    iconUrl = ""
    status = 3
    tvTypes = listOf("Movie")
}

dependencies {
    implementation("com.github.recloudstream.cloudstream:library:master-SNAPSHOT")
}
