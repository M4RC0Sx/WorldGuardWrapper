plugins {
    java
}

description = "WorldGuard version 6 implementation."

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.16.4-R0.1-SNAPSHOT")
    compileOnly("com.sk89q.worldguard:worldguard-legacy:6.2")
    compileOnly("com.sk89q.worldedit:worldedit-core:6.0")
    compileOnly(project(":Layer"))
}