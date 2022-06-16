plugins {
    kotlin("multiplatform") version kotlinVersion apply false
    kotlin("plugin.serialization") version kotlinVersion apply false
}

allprojects {
    group = "io.github.alexoooo.sample.lib"
    version = "0.4.1-SNAPSHOT"

    repositories {
        mavenCentral()
        mavenLocal()
    }
}
