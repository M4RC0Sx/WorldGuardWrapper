plugins {
    java
}

description = "Layer containing the abstraction base for our WorldGuard wrapper."

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.16.5-R0.1-SNAPSHOT")
    // we'll include the worldguard legacy from 6.2 just because we need the ProtectedRegion and ApplicableRegionSet.
    compileOnly("com.sk89q.worldguard:worldguard-legacy:6.2")
}