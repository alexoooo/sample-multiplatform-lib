import org.jetbrains.kotlin.gradle.targets.js.yarn.yarn
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform")
    `maven-publish`
}


kotlin {
    js {
        useCommonJs()
        browser()
    }

    sourceSets {
        jsMain {
            dependencies {
                implementation(project(":lib-common"))

                api("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:$coroutinesVersion")

                implementation(npm("core-js", coreJsVersion))
            }
        }

        jsTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}


publishing {
    repositories {
        mavenLocal()
    }
}


// https://youtrack.jetbrains.com/issue/KT-52578/KJS-Gradle-KotlinNpmInstallTask-gradle-task-produces-unsolvable-warning-ignored-scripts-due-to-flag.
yarn.ignoreScripts = false