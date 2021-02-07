package me.rarlab.worldguard.helper;

import me.rarlab.worldguard.WorldGuardLayer;
import me.rarlab.worldguard.version6.Implementation;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

/**
 * This class will just be a utility class to help fetch the correct version.
 */
public final class Fetcher {
    // no need for a constructor
    private Fetcher() {
        throw new IllegalStateException("Fetcher instantiation attempt");
    }

    /**
     * Fetch the corresponding version.
     *
     * @return {@link WorldGuardLayer}
     */
    public static WorldGuardLayer<?> fetch() {
        final Plugin plugin = Bukkit.getPluginManager().getPlugin("WorldGuard");
        if (plugin == null) return new DummyImplementation();

        final int version = Integer.parseInt(plugin.getDescription().getVersion().substring(0, 1)); // (6).1.3-SNAPSHOT;c904242
        return version <= 6 ? Implementation.generate() : me.rarlab.worldguard.version7.Implementation.generate();
    }
}