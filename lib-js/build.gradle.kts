plugins {
    id("org.jetbrains.kotlin.js")
}

repositories {
    maven { setUrl("https://dl.bintray.com/kotlin/kotlin-dev") }
    maven { setUrl("https://dl.bintray.com/kotlin/kotlin-eap") }
    jcenter()
    maven { setUrl("https://dl.bintray.com/kotlin/kotlin-js-wrappers") }
    maven { setUrl("https://dl.bintray.com/kotlin/kotlinx") }
    mavenCentral()
}

//val kotlin_version = "pre.94-kotlin-1.3.70" // for kotlin-wrappers
val kotlinx_serialization_version = "0.20.0"

kotlin {
    target {
        useCommonJs()
        browser()
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-js")

//    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-js:$kotlinx_serialization_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:1.3.5")

    implementation(project(":lib-common"))

    implementation(npm("core-js", "2.6.5"))
//    implementation("org.jetbrains.kotlinx:kotlinx-html:0.6.12")
//    implementation("org.jetbrains:kotlin-react:16.13.0-$kotlin_version")
//    implementation("org.jetbrains:kotlin-react-dom:16.13.0-$kotlin_version")
//    implementation("org.jetbrains:kotlin-styled:1.0.0-$kotlin_version")
//    implementation("org.jetbrains:kotlin-extensions:1.0.1-$kotlin_version")
//    implementation("org.jetbrains:kotlin-css-js:1.0.0-$kotlin_version")
//    implementation(npm("react", "16.13.0"))
//    implementation(npm("react-dom", "16.13.0"))
//    implementation(npm("react-is", "16.13.0"))
//    implementation(npm("inline-style-prefixer", "5.1.0"))
//    implementation(npm("styled-components", "4.3.2"))
    testImplementation("org.jetbrains.kotlin:kotlin-test-js")
//    testImplementation(npm("enzyme", "3.9.0"))
//    testImplementation(npm("enzyme-adapter-react-16", "1.12.1"))
}

run {
//    project(":proj-jvm").afterEvaluate {
//        dependsOn project(":proj-jvm").tasks.getByName('prepareDevServer')
//    }
}