plugins {
    java
    `java-library`
    `maven-publish`
    id("com.github.hierynomus.license-base") version "0.16.1"
}

group = "io.github.pulsebeat02"
version = "v1.1.0"

repositories {
    mavenCentral()
}

dependencies {
    compileOnlyApi("com.google.guava:guava:31.0.1-jre")
    testRuntimeOnly("com.google.guava:guava:31.0.1-jre")
    compileOnlyApi("uk.co.caprica:vlcj:4.7.1")
    testRuntimeOnly("uk.co.caprica:vlcj:4.7.1")
    compileOnlyApi("uk.co.caprica:vlcj-natives:4.1.0")
    testRuntimeOnly("uk.co.caprica:vlcj-natives:4.1.0")
    compileOnlyApi("net.java.dev.jna:jna:5.9.0")
    testRuntimeOnly("net.java.dev.jna:jna:5.9.0")
    compileOnlyApi("net.java.dev.jna:jna-platform:5.9.0")
    testRuntimeOnly("net.java.dev.jna:jna-platform:5.9.0")
}

sourceSets {
    main {
        java {
            srcDir("src/main/java")
        }
        resources {
            srcDir("src/main/resources")
        }
    }
}

tasks {
    java {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    publish {
        dependsOn(clean)
        dependsOn(build)
    }
}

publishing {
    repositories {
        maven {
            setUrl("https://pulsebeat02.jfrog.io/artifactory/pulse-gradle-release-local/")
            credentials {
                username = ""
                password = ""
            }
        }
    }
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}

subprojects {

    apply(plugin = "com.github.hierynomus.license-base")

    license {
        header = rootProject.file("LICENSE")
        encoding = "UTF-8"
        mapping("java", "SLASHSTAR_STYLE")
        includes(listOf("**/*.java", "**/*.kts"))
    }

    task<Wrapper>("wrapper") {
        gradleVersion = "7.3"
    }
}