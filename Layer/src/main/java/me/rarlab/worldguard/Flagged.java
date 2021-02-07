package me.rarlab.worldguard;

import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * This interface is the flagged layer for all "flag" related functions.
 *
 * @since 1.0
 */
public interface Flagged {
    /**
     * Get whether or not a player can perform an action at a specific location.
     *
     * @param player {@link Player} whom to check this for.
     * @param location {@link Location} where this check will commence.
     * @param flagName {@link String} the name of the StateFlag to check for.
     * @param defaultValue {@link Boolean} the default value to be assigned if the flag
     *                                    does not exist or is not an instance of StateFlag.
     * @return {@link Boolean}
     */
    boolean canPerformCustom(Player player, Location location, String flagName, boolean defaultValue);

    /**
     * Get whether or not a player can perform an action at a specific location.
     *
     * @param player {@link Player} whom to check this for.
     * @param location {@link Location} where this check will commence.
     * @param flag {@link DefaultRegionFlag} the flag to check.
     * @param defaultValue {@link Boolean} the default value to be assigned if the flag
     *                                    does not exist or is not an instance of StateFlag.
     * @return {@link Boolean}
     */
    boolean canPerform(Player player, Location location, DefaultRegionFlag flag, boolean defaultValue);

    /**
     * Get whether or not a player can perform an action at a specific chunk by coordinates.
     *
     * @param player {@link Player} whom to check this for.
     * @param world {@link String} which world this chunk belongs to.
     * @param chunkX {@link Integer} the X coordinate of the chunk to check.
     * @param chunkZ {@link Integer} the Z coordinate of the chunk to check.
     * @param flagName {@link String} the name of the StateFlag to check for.
     * @param defaultValue {@link Boolean} the default value to be assigned if the flag
     *                                    does not exist or is not an instance of StateFlag.
     * @return {@link Boolean}
     */
    boolean canPerformCustom(Player player, String world, int chunkX, int chunkZ, String flagName, boolean defaultValue);

    /**
     * Get whether or not a player can perform an action at a specific chunk by coordinates.
     *
     * @param player {@link Player} whom to check this for.
     * @param world {@link String} which world this chunk belongs to.
     * @param chunkX {@link Integer} the X coordinate of the chunk to check.
     * @param chunkZ {@link Integer} the Z coordinate of the chunk to check.
     * @param flag {@link DefaultRegionFlag} the flag to check.
     * @param defaultValue {@link Boolean} the default value to be assigned if the flag
     *                                    does not exist or is not an instance of StateFlag.
     * @return {@link Boolean}
     */
    boolean canPerform(Player player, String world, int chunkX, int chunkZ, DefaultRegionFlag flag, boolean defaultValue);

    /**
     * Register a new flag by name and default value.
     *
     * @param name {@link String} the name of this flag.
     * @param defaultValue {@link Boolean} the default value of this flag.
     * @return {@link Boolean}
     */
    boolean registerFlag(String name, boolean defaultValue);
}