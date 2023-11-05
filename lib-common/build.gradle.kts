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
            testTask {
                testLogging {
                    showExceptions = true
                    exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
                    showCauses = true
                    showStackTraces = true
                }
            }
        }
    }

    sourceSets {
        commonMain {}

        commonTest {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }


        jvmMain {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$serializationVersion")
                implementation("org.jetbrains.kotlin:kotlin-reflect")
                implementation("com.github.andrewoma.dexx:collection:$dexxVersion")
            }
        }

        jvmTest {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
            }
        }


        jsMain {
            dependencies {
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:$coroutinesVersion")
                implementation(npm("immutable", immutaleJsVersion))
            }
        }

        jsTest {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
    }
}


publishing {
    repositories {
        mavenLocal()
    }
}