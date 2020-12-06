plugins {
    kotlin("jvm") version "1.4.10"
    application
}

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    implementation("com.google.guava:guava:30.0-jre")
    implementation("com.github.ajalt.clikt:clikt:3.0.1")
    implementation("com.github.ajalt.mordant:mordant:2.0.0-alpha1")
    implementation("com.itextpdf:itext7-core:7.1.13")
    implementation("org.slf4j:slf4j-simple:1.7.30")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("com.github.pdf.unlocker.AppKt")
}
