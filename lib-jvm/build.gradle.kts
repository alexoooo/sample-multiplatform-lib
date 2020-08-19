import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
//import org.springframework.boot.gradle.tasks.bundling.BootJar
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpack


plugins {
    kotlin("jvm")
}


dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
//    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:$serializationVersion")

    implementation(project(":lib-common"))

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}


tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}


// http://bastienpaul.fr/wordpress/2019/02/08/publish-a-kotlin-lib-with-gradle-kotlin-dsl/
// https://stackoverflow.com/questions/52596968/build-source-jar-with-gradle-kotlin-dsl/
tasks {
    val sourcesJar by creating(Jar::class) {
        archiveClassifier.set("sources")
        from(sourceSets.getByName("main").allSource)
    }

    artifacts {
        archives(sourcesJar)
        archives(jar)
    }
}