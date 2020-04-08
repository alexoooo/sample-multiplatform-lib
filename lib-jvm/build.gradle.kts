import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpack


plugins {
    id("org.springframework.boot") version "2.2.6.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    kotlin("jvm") //version "1.3.71"
    kotlin("plugin.spring") version "1.3.71"
}


repositories {
    maven { setUrl("https://dl.bintray.com/kotlin/kotlin-dev") }
    maven { setUrl("https://dl.bintray.com/kotlin/kotlin-eap") }
    jcenter()
    maven { setUrl("https://dl.bintray.com/kotlin/kotlin-js-wrappers") }
    maven { setUrl("https://dl.bintray.com/kotlin/kotlinx") }
    mavenCentral()
}

val kotlin_version = "pre.94-kotlin-1.3.70" // for kotlin-wrappers
val logback_version = "1.2.3"
val kotlinx_serialization_version = "0.20.0"


dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("org.jetbrains:kotlin-css-jvm:1.0.0-$kotlin_version")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:$kotlinx_serialization_version")

    implementation(project(":lib-common"))

    implementation("org.springframework.boot:spring-boot-starter-webflux")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}


tasks.withType<ProcessResources> {
    val jsProject = project(":lib-js")
    val task = jsProject.tasks.getByName("browserProductionWebpack") as KotlinWebpack

    from(task.destinationDirectory!!) {
        into("public")
    }

    dependsOn(project(":lib-js").tasks.getByName("browserProductionWebpack"))
}


tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}


tasks.getByName<Jar>("jar") {
    enabled = true
}

tasks.getByName<BootJar>("bootJar") {
    archiveClassifier.set("boot")
}

