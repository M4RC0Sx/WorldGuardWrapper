package me.rarlab.worldguard;

import org.bukkit.Location;

import java.util.function.BiPredicate;

/**
 * This interface is the regional layer for all region related functions.
 *
 * Some methods contained in this interface will have
 * parameters of type {@link Object} with the one and only reason that
 * 6.2 and 7.0.0 might have differences in ApplicableRegionSet and ProtectedRegion.
 * These objects can just be cast to it's corresponding type at your own pace.
 * The differences should not be big AT ALL, so there probably isn't too much
 * to care for, one should be able to use 7.0.0 as the producer but still bound to
 * 6.2 without any issues whatsoever.
 *
 * @since 1.0
 */
public interface Regional {
    /**
     * Get whether or not a passed down location is inside of
     * a specific region passed by id.
     *
     * Passing <b>null</b> to 'regionId' will check if there's
     * <b>ANY</b> region at that location, no matter id. This is precisely
     * what {@link Regional#inRegion(Location)} is invoking.
     *
     * @param location {@link Location} where to check at.
     * @param regionId {@link String} id of the region to check.
     * @return {@link Boolean}
     */
    boolean inRegion(Location location, String regionId);

    /**
     * Get whether or not a passed down location is inside of any region.
     *
     * @param location {@link Location} where to check at.
     * @return {@link Boolean}
     */
    boolean inRegion(Location location);

    /**
     * Get whether or not a passed down location is inside of
     * a specific region passed by id as well as meets the
     * provided predicate's standard.
     *
     * @param location {@link Location} the location to check.
     * @param regionId {@link String} id of the region to check.
     * @param predicate {@link BiPredicate} predicate to provide and test,
     *                                     first object is the region set and
     *                                     second object is the protected region involved.
     * @return {@link Boolean}
     */
    boolean inRegionAnd(Location location, String regionId, BiPredicate<Object, Object> predicate);

    /**
     * Get whether or not a passed down lazy chunk contains a specific region by it's id.
     *
     * Passing <b>null</b> to 'regionId' will check if there's
     * <b>ANY</b> region at that chunk, no matter id. This is precisely
     * what {@link Regional#hasRegion(String, int, int)} is invoking.
     *
     * @param world {@link String} name of the world this check will commence in.
     * @param chunkX {@link Integer} x coordinate of the chunk.
     * @param chunkZ {@link Integer} z coordinate of the chunk.
     * @param regionId {@link String} id of the region to attempt to find.
     * @return {@link Boolean}
     */
    boolean hasRegion(String world, int chunkX, int chunkZ, String regionId);

    /**
     * Get whether or not a passed down lazy chunk contains any region.
     *
     * @param world {@link String} name of the world this check will commence in.
     * @param chunkX {@link Integer} x coordinate of the chunk.
     * @param chunkZ {@link Integer} z coordinate of the chunk.
     * @return {@link Boolean}
     */
    boolean hasRegion(String world, int chunkX, int chunkZ);
}