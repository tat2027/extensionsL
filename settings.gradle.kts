pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven {
            url = uri("https://jitpack.io")
            credentials {
                username = System.getenv("JITPACK_TOKEN") ?: ""
            }
        }
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://jitpack.io")
            credentials {
                username = System.getenv("JITPACK_TOKEN") ?: ""
            }
        }
    }
}

rootProject.name = "extensionsL"
include(":movi.pk")
