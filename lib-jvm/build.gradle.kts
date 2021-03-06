import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    kotlin("jvm")
    `maven-publish`
}


dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
//    implementation("ch.qos.logback:logback-classic:$logbackVersion")
//    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:$serializationVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$serializationVersion")

    implementation(project(":lib-common"))

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}


tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")

//        jvmTarget = "11"
        jvmTarget = "13"

//        useIR = true
    }
}


// https://stackoverflow.com/questions/61432006/building-an-executable-jar-that-can-be-published-to-maven-local-repo-with-publi
val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

// http://bastienpaul.fr/wordpress/2019/02/08/publish-a-kotlin-lib-with-gradle-kotlin-dsl/
// https://stackoverflow.com/questions/52596968/build-source-jar-with-gradle-kotlin-dsl/
//tasks {
//    val sourcesJar by creating(Jar::class) {
//        archiveClassifier.set("sources")
//        from(sourceSets.getByName("main").allSource)
//    }
//
//    artifacts {
//        archives(sourcesJar)
//        archives(jar)
//    }
//}

publishing {
    repositories {
        mavenLocal()
    }

    publications {
        create<MavenPublication>("jvm") {
            from(components["java"])
            artifact(sourcesJar.get())
        }
    }
}