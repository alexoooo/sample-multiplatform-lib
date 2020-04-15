plugins {
    id("org.jetbrains.kotlin.js")
}


kotlin {
    target {
        useCommonJs()
        browser()
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-js")

//    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-js:$kotlinx_serialization_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:$coroutinesVersion")

    implementation(project(":lib-common"))

    implementation(npm("core-js", coreJsVersion))
    testImplementation("org.jetbrains.kotlin:kotlin-test-js")
}

run {
//    project(":proj-jvm").afterEvaluate {
//        dependsOn project(":proj-jvm").tasks.getByName('prepareDevServer')
//    }
}