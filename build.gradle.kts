plugins {
    kotlin("multiplatform") version kotlinVersion apply false
    kotlin("plugin.serialization") version kotlinVersion apply false
}

allprojects {
    version = "0.0.2-SNAPSHOT"
}
