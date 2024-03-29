plugins {
    kotlin("jvm") version "1.9.21"
}

group = "com.aetherealtech"
version = "0.0.2"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}