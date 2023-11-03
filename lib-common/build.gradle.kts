import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform")
    id("kotlinx-serialization")
    `maven-publish`
}


kotlin {
    jvm {}

    js {
        browser {
            testTask(Action {
                testLogging {
                    showExceptions = true
                    exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
                    showCauses = true
                    showStackTraces = true
                }
            })
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
//                implementation(kotlin("stdlib-common"))
//                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:$serializationVersion")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }


        val jvmMain by getting {
            dependencies {
//                implementation(kotlin("stdlib-jdk8"))
//                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:$serializationVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$serializationVersion")
                implementation("org.jetbrains.kotlin:kotlin-reflect")
                implementation("com.github.andrewoma.dexx:collection:$dexxVersion")
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
            }
        }


        val jsMain by getting {
            dependencies {
                // NB: seems to be required for IntelliJ IDEA 2020.2.2, but compiles from gradle without it
                implementation(kotlin("stdlib-js"))

//                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-js:$serializationVersion")
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:$coroutinesVersion")
                implementation(npm("immutable", immutaleJsVersion))
            }
        }

        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
    }
}


tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        freeCompilerArgs = freeCompilerArgs + "-Xexpect-actual-classes"
    }
}


publishing {
    repositories {
        mavenLocal()
    }

//    publications {
//        create<MavenPublication>("common") {
////            println("Components: " + components.asMap.keys)
//            from(components["kotlin"])
//        }
//    }
}