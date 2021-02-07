package me.rarlab.worldguard;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Set;

/**
 * This abstract class is the abstraction layer for all base WorldGuard versions.
 *
 * @since 1.0
 */
public abstract class WorldGuardLayer<T> implements Regional, Flagged {
    /** {@link Object} instance of the layered version. **/
    protected final T instance;

    /** {@link Boolean} whether or not this layer is hooked. **/
    public final boolean hooked;

    /**
     * Primary Constructor.
     *
     * @param instance {@link Object} the WorldGuard instance to initialize.
     * @param hooked {@link Boolean} whether or not this layer is hooked.
     */
    public WorldGuardLayer(final Object instance, final boolean hooked) {
        this.instance = (T) instance;
        this.hooked = hooked;
    }

    /**
     * Get corresponding ApplicableRegionSet from passed down location.
     *
     * @param location {@link Location} where to fetch region set from.
     * @return {@link Object}
     */
    public abstract Object getRegionSetAt(Location location);

    /**
     * Get corresponding ApplicableRegionSet from passed down chunk coordinates.
     *
     * @param world {@link String} the name of the world this chunk belongs to.
     * @param chunkX {@link Integer} the chunk's X coordinate.
     * @param chunkZ {@link Integer} the chunk's Z coordinate.
     * @return {@link Object}
     */
    public abstract Object getRegionSetAt(String world, int chunkX, int chunkZ);

    /**
     * Get all regions applicable at a certain location.
     *
     * @param location {@link Location} where to fetch all regions at.
     * @return {@link Set<Object>}
     */
    public abstract Set<Object> getRegionsAt(Location location);

    /**
     * Get all regions applicable in a certain chunk.
     *
     * @param world {@link String} the name of the world this chunk belongs to.
     * @param chunkX {@link Integer} the chunk's X coordinate.
     * @param chunkZ {@link Integer} the chunk's Z coordinate.
     * @return {@link Set<Object>}
     */
    public abstract Set<Object> getRegionsAt(String world, int chunkX, int chunkZ);

    /**
     * Get a player's corresponding LocalPlayer instance.
     *
     * @param player {@link Player} whom's LocalPlayer instance to fetch.
     * @return {@link Object} 
     */
    protected abstract Object getPlayer(Player player);

    /**
     * Get whether or not WorldGuard is present.
     *
     * @return {@link Boolean}
     */
    public boolean isNotPresent() {
        return this.instance == null;
    }
}