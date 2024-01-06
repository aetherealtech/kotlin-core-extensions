plugins {
    kotlin("jvm")
    `maven-publish`
    signing
}

group = "com.aetherealtech"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}

java {
    withJavadocJar()
    withSourcesJar()
}

kotlin {
    jvmToolchain(11)
}

val NEXUS_USERNAME: String by properties
val NEXUS_PASSWORD: String by properties

publishing {
    repositories {
        maven {
            credentials {
                username = NEXUS_USERNAME
                password = NEXUS_PASSWORD
            }
            val releasesRepoUrl = "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"
            val snapshotsRepoUrl = "https://s01.oss.sonatype.org/content/repositories/snapshots/"
            url = uri(if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)
        }
    }

    publications {
        create<MavenPublication>("Maven") {
            from(components["java"])
        }
        withType<MavenPublication> {
            pom {
                packaging = "jar"
                name.set("tuples")
                description.set("")
                url.set("github.com/aetherealtech/kotlin-core-extensions/tuples")
                licenses {
                    license {
                        name.set("GPLv3 license")
                        url.set("https://www.gnu.org/licenses/gpl-3.0.en.html")
                    }
                }
                issueManagement {
                    system.set("Github")
                    url.set("https://github.com/aetherealtech/kotlin-core-extensions/issues")
                }
                scm {
                    connection.set("scm:git:git://github.com/aetherealtech/kotlin-core-extensions.git/tuples")
                    developerConnection.set("scm:git:git@github.com:aetherealtech/kotlin-core-extensions.git/tuples")
                    url.set("https://github.com/aetherealtech/kotlin-core-extensions/tuples")
                }
                developers {
                    developer {
                        name.set("Dan Coleman")
                        email.set("dan@aetherealtech.com")
                    }
                }
            }
        }
    }
}

signing {
    sign(publishing.publications)
}