import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform") version kotlinVersion apply false
    kotlin("plugin.serialization") version kotlinVersion apply false
}

allprojects {
    group = "io.github.alexoooo.sample.lib"
    version = "0.6.0-SNAPSHOT"

    repositories {
        mavenCentral()
        mavenLocal()
    }
}


allprojects {
//    tasks.withType(KotlinCompile::class).all {
//        kotlinOptions {
//            freeCompilerArgs += "-Xexpect-actual-classes"
//        }
//    }

//    tasks.configureEach<KotlinCompile<*>> {
//        kotlinOptions {
//            freeCompilerArgs += listOf(
//                "-Xexpect-actual-classes",
//            )
//        }
//    }
}

