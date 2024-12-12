plugins {
    kotlin("jvm") version "1.9.24"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("it.unibo.tuprolog.argumentation:arg2p-jvm:0.8.3")
    implementation("it.unibo.tuprolog:solve-classic-jvm:1.0.4")
    implementation("it.unibo.tuprolog:parser-theory-jvm:1.0.4")
    implementation("it.unibo.tuprolog:dsl-solve-jvm:1.0.4")
}

application {
    mainClass = "org.gradle.sample.MainKt"
}
