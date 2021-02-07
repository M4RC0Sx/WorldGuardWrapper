package me.rarlab.worldguard.helper;

import me.rarlab.worldguard.DefaultRegionFlag;
import me.rarlab.worldguard.WorldGuardLayer;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Set;
import java.util.function.BiPredicate;

/**
 * Dummy implementation of {@link WorldGuardLayer<Object>}.
 */
public final class DummyImplementation extends WorldGuardLayer<Object> {
    public DummyImplementation() {
        super(null, false);
    }

    @Override
    public Object getRegionSetAt(Location location) {
        return null;
    }

    @Override
    public Object getRegionSetAt(String world, int chunkX, int chunkZ) {
        return null;
    }

    @Override
    public Set<Object> getRegionsAt(Location location) {
        return null;
    }

    @Override
    public Set<Object> getRegionsAt(String world, int chunkX, int chunkZ) {
        return null;
    }

    @Override
    protected Object getPlayer(Player player) {
        return null;
    }

    @Override
    public boolean canPerformCustom(Player player, Location location, String flagName, boolean defaultValue) {
        return defaultValue;
    }

    @Override
    public boolean canPerform(Player player, Location location, DefaultRegionFlag flag, boolean defaultValue) {
        return defaultValue;
    }

    @Override
    public boolean canPerformCustom(Player player, String world, int chunkX, int chunkZ, String flagName, boolean defaultValue) {
        return defaultValue;
    }

    @Override
    public boolean canPerform(Player player, String world, int chunkX, int chunkZ, DefaultRegionFlag flag, boolean defaultValue) {
        return defaultValue;
    }

    @Override
    public boolean registerFlag(String name, boolean defaultValue) {
        return false;
    }

    @Override
    public boolean inRegion(Location location, String regionId) {
        return true;
    }

    @Override
    public boolean inRegion(Location location) {
        return true;
    }

    @Override
    public boolean inRegionAnd(Location location, String regionId, BiPredicate<Object, Object> predicate) {
        return true;
    }

    @Override
    public boolean hasRegion(String world, int chunkX, int chunkZ, String regionId) {
        return true;
    }

    @Override
    public boolean hasRegion(String world, int chunkX, int chunkZ) {
        return true;
    }
}