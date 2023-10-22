import org.jetbrains.kotlin.gradle.targets.js.yarn.yarn

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
        val jsMain by getting {
            dependencies {
                implementation(project(":lib-common"))

                api("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:$coroutinesVersion")

                implementation(npm("core-js", coreJsVersion))
            }
        }

        val jsTest by getting {
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

//    publications {
//        create<MavenPublication>("js") {
//            from(components["kotlin"])
//        }
//    }
}


// https://youtrack.jetbrains.com/issue/KT-52578/KJS-Gradle-KotlinNpmInstallTask-gradle-task-produces-unsolvable-warning-ignored-scripts-due-to-flag.
yarn.ignoreScripts = false