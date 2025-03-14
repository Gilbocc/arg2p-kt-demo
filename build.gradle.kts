plugins {
    kotlin("jvm") version "2.1.10"
    application
}

group = "it.unibo.tuprolog.argumentation.demo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("it.unibo.tuprolog.argumentation:arg2p-jvm:0.10.0")
}

application {
    mainClass = "it.unibo.tuprolog.argumentation.demo.MainKt"
}
