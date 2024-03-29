pluginManagement {
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "kotlinx-serialization") {
                useModule("org.jetbrains.kotlin:kotlin-serialization:${requested.version}")
            }
        }
    }

//    repositories {
//        maven { setUrl("https://dl.bintray.com/kotlin/kotlin-dev") }
//        maven { setUrl("https://dl.bintray.com/kotlin/kotlin-eap") }
//        mavenCentral()
//        maven { setUrl("https://plugins.gradle.org/m2/") }
//    }
}

rootProject.name = "lib"

include("lib-common", "lib-js", "lib-jvm")