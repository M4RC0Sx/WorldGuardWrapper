package me.rarlab.worldguard;

/**
 * This enumeration is a replacement for WorldGuard's flag registry's defaults.
 */
public enum DefaultRegionFlag {
    /**
     * Default values.
     */
    PASS_THROUGH("PASSTHROUGH"), BUILD(null),
    BLOCK_BREAK(null), BLOCK_PLACE(null),
    USE(null), INTERACT(null),
    DAMAGE_ANIMALS(null), PVP(null),
    SLEEP(null), TNT(null),
    CHEST_ACCESS(null), PLACE_VEHICLE(null),
    DESTROY_VEHICLE(null), LIGHTER(null),
    RIDE(null), POTION_SPLASH(null),
    ITEM_FRAME_ROTATE(null), TRAMPLE_BLOCKS(null),
    ITEM_PICKUP(null), ITEM_DROP(null),
    EXP_DROPS(null), MOB_DAMAGE(null),
    CREEPER_EXPLOSION(null), ENDER_DRAGON_BLOCK_DAMAGE("ENDERDRAGON_BLOCK_DAMAGE"),
    GHAST_FIREBALL(null), FIREWORK_DAMAGE(null),
    OTHER_EXPLOSION(null), WITHER_DAMAGE(null),
    ENDER_BUILD(null), SNOWMAN_TRAILS(null),
    ENTITY_PAINTING_DESTROY(null), ENTITY_ITEM_FRAME_DESTROY(null),
    MOB_SPAWNING(null), DENY_SPAWN(null),
    PISTONS(null), FIRE_SPREAD(null),
    LAVA_FIRE(null), LIGHTNING(null),
    SNOW_FALL(null), SNOW_MELT(null),
    ICE_FORM(null), ICE_MELT(null),
    FROSTED_ICE_MELT(null), FROSTED_ICE_FORM(null),
    MUSHROOMS(null), LEAF_DECAY(null),
    GRASS_SPREAD(null), MYCELIUM_SPREAD(null),
    VINE_GROWTH(null), CROP_GROWTH(null),
    SOIL_DRY(null), WATER_FLOW(null),
    LAVA_FLOW(null), WEATHER_LOCK(null),
    TIME_LOCK(null), SEND_CHAT(null),
    RECEIVE_CHAT(null), BLOCKED_COMMANDS("BLOCKED_CMDS"),
    ALLOWED_COMMANDS("ALLOWED_CMDS"), TELEPORT_LOCATION("TELE_LOC"),
    SPAWN_LOCATION("SPAWN_LOC"), INVINCIBILITY(null),
    FALL_DAMAGE(null), ENTRY(null),
    EXIT(null), EXIT_OVERRIDE(null),
    EXIT_VIA_TELEPORT(null), ENDER_PEARL("ENDERPEARL"),
    CHORUS_TELEPORT(null), GREET_MESSAGE(null),
    FAREWELL_MESSAGE(null), GREET_TITLE(null),
    FAREWELL_TITLE(null), NOTIFY_ENTER(null),
    NOTIFY_LEAVE(null), GAME_MODE(null),
    HEAL_DELAY(null), HEAL_AMOUNT(null),
    MINIMUM_HEAL("MIN_HEAL"), MAXIMUM_HEAL("MAX_HEAL"),
    FEED_DELAY(null), FEED_AMOUNT(null),
    MINIMUM_FOOD("MIN_FOOD"), MAXIMUM_FOOD("MAX_FOOD"),
    DENY_MESSAGE(null), ENTRY_DENY_MESSAGE(null),
    EXIT_DENY_MESSAGE(null);

    /**
     * {@link String} this field contains the value of a Flag's replacement, if they have one.
     * The replacement is there cause of wording differences in this enumeration and WorldGuard's.
     */
    public final String replacement;

    /**
     * Primary constructor.
     *
     * @param replacement {@link String} corresponding Flag's replacement.
     */
    DefaultRegionFlag(final String replacement) {
        this.replacement = replacement;
    }
}