plugins {
    id("org.jetbrains.kotlin.js")
    `maven-publish`
}


kotlin {
//    js (IR) {
//        useCommonJs()
//        browser()
//    }

    js {
        useCommonJs()
        browser()
    }

//    target {
//        useCommonJs()
//        browser()
//    }
}


dependencies {
    implementation(project(":lib-common"))
//    implementation("org.jetbrains.kotlin:kotlin-stdlib-js")

    api("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:$coroutinesVersion")

    implementation(npm("core-js", coreJsVersion))
//    testImplementation("org.jetbrains.kotlin:kotlin-test-js")
    testImplementation(kotlin("test"))
}


publishing {
    repositories {
        mavenLocal()
    }

    publications {
        create<MavenPublication>("js") {
//            println("Components: " + components.asMap.keys)
            from(components["kotlin"])
        }
    }
}