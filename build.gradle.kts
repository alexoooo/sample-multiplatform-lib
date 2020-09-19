plugins {
    kotlin("multiplatform") version kotlinVersion apply false
    kotlin("plugin.serialization") version kotlinVersion apply false
}

allprojects {
    group = "io.github.alexoooo.sample.lib"
    version = "0.2.1-SNAPSHOT"

    repositories {
        maven { setUrl("https://dl.bintray.com/kotlin/kotlin-dev") }
        maven { setUrl("https://dl.bintray.com/kotlin/kotlin-eap") }
        jcenter()
        maven { setUrl("https://dl.bintray.com/kotlin/kotlin-js-wrappers") }
        maven { setUrl("https://dl.bintray.com/kotlin/kotlinx") }
        maven { setUrl("https://kotlin.bintray.com/kotlinx/") }
        mavenCentral()
    }
}
