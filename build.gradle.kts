allprojects {
    group = "com.github.officialrarlab"
    version = "1.0"
}

plugins {
    java
    id("com.github.johnrengelman.shadow") version "5.2.0"
}

subprojects {
    repositories {
        mavenLocal()
        mavenCentral()
        maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots")
        maven("https://oss.sonatype.org/content/groups/public/")
        maven("https://maven.enginehub.org/repo/")
        maven("https://jitpack.io")
    }
}

repositories {
    mavenCentral()
    mavenLocal()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots")
    maven("https://oss.sonatype.org/content/groups/public/")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.16.4-R0.1-SNAPSHOT")
    compileOnly("com.sk89q.worldguard:worldguard-bukkit:7.0.0")
    implementation(project(":Layer"))
    implementation(project(":Version6"))
    implementation(project(":Version7"))
}