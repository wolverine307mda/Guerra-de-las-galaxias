// Comportamiento para el proyecto
plugins {
    kotlin("jvm") version "1.9.0"
    application
    // Plugin de Dokka para la documentación
    id("org.jetbrains.dokka") version "1.9.0"
}
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.github.ajalt.mordant:mordant:2.2.0")
    implementation("com.github.ajalt.mordant:mordant-jvm:2.2.0")
    implementation("net.java.dev.jna:jna:5.13.0")

    // Si quieres usar la beta 9, no neceitas nada mas que esta
    // implementation("com.github.ajalt.mordant:mordant-jvm:2.0.0-beta9")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "MainKt"
    }
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}