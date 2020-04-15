import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
//import org.springframework.boot.gradle.tasks.bundling.BootJar
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpack


plugins {
    kotlin("jvm")
}


dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
//    implementation("org.jetbrains:kotlin-css-jvm:1.0.0-$wrapperKotlinVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:$serializationVersion")

    implementation(project(":lib-common"))

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

//tasks.getByName<BootJar>("bootJar") {
//    archiveClassifier.set("boot")
//}

