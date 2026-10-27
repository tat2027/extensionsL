dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

rootProject.name = "CloudstreamPlugins"

val disabled = listOf<String>()

File(rootDir, ".").eachDir { dir ->
    // Check for build.gradle or build.gradle.kts
    if (!disabled.contains(dir.name) && (File(dir, "build.gradle.kts").exists() || File(dir, "build.gradle").exists())) {
        include(dir.name)
    }
}

fun File.eachDir(block: (File) -> Unit) {
    listFiles()?.filter { it.isDirectory }?.forEach { block(it) }
}
