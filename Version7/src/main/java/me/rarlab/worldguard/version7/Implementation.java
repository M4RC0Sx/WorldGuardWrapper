package me.rarlab.worldguard.version7;

import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import me.rarlab.worldguard.DefaultRegionFlag;
import me.rarlab.worldguard.WorldGuardLayer;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Function;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.sk89q.worldedit.bukkit.BukkitAdapter.adapt;
import static com.sk89q.worldedit.math.BlockVector3.ZERO;
import static com.sk89q.worldedit.math.BlockVector3.at;

/**
 * WorldGuard version 7 implementation of Layer.
 */
public final class Implementation extends WorldGuardLayer<WorldGuard> {
    /**
     * Primary constructor.
     * 
     * @param instance {@link WorldGuard} the corresponding instance.
     */
    private Implementation(final WorldGuard instance) {
        super(instance, true);
    }

    @Override
    public Object getRegionSetAt(Location location) {
        if (this.isNotPresent()) return null;
        return this.query(location.getWorld(), manager -> manager.getApplicableRegions(this.vectorAt(location)));
    }

    @Override
    public Object getRegionSetAt(String world, int chunkX, int chunkZ) {
        final World exactWorld = Bukkit.getWorld(world);
        if (this.isNotPresent() || exactWorld == null) return null;
        return this.query(exactWorld, manager -> {
            final BlockVector3 minVector = at(chunkX << 4, 0, chunkZ << 4);
            final BlockVector3 maxVector = at(minVector.getX() + 15, exactWorld.getMaxHeight(), minVector.getZ() + 15);
            final ProtectedCuboidRegion cuboidRegion = new ProtectedCuboidRegion("wg_wrapper_overlap", minVector, maxVector);
            return manager.getApplicableRegions(cuboidRegion);
        });
    }

    @Override
    public Set<Object> getRegionsAt(Location location) {
        if (this.isNotPresent()) return new HashSet<>();
        final ApplicableRegionSet regionSet = (ApplicableRegionSet) this.getRegionSetAt(location);
        return new HashSet<>(regionSet.getRegions());
    }

    @Override
    public Set<Object> getRegionsAt(String world, int chunkX, int chunkZ) {
        final World exactWorld = Bukkit.getWorld(world);
        if (this.isNotPresent() || exactWorld == null) return new HashSet<>();

        final ApplicableRegionSet regionSet = (ApplicableRegionSet) this.getRegionSetAt(world, chunkX, chunkZ);
        return new HashSet<>(regionSet.getRegions());
    }

    @Override
    protected Object getPlayer(Player player) {
        return WorldGuardPlugin.inst().wrapPlayer(player);
    }

    @Override
    public boolean inRegion(Location location, String regionId) {
        final Set<Object> regions = this.getRegionsAt(location);
        if (regionId == null) return regions.size() > 0;

        for (final Object regionObject : regions) {
            final ProtectedRegion region = (ProtectedRegion) regionObject;
            if (region.getId().equalsIgnoreCase(regionId)) return true;
        }
        return false;
    }

    @Override
    public boolean inRegion(Location location) {
        return this.inRegion(location, null);
    }

    @Override
    public boolean inRegionAnd(Location location, String regionId, BiPredicate<Object, Object> predicate) {
        if (this.isNotPresent()) return false;

        final ApplicableRegionSet regionSet = (ApplicableRegionSet) this.getRegionSetAt(location);
        final Collection<ProtectedRegion> regions = regionSet.getRegions();
        final Optional<ProtectedRegion> region = regions.stream().filter(it -> it.getId().equalsIgnoreCase(regionId)).findFirst();

        return region.isPresent() && predicate.test(regionSet, region.get());
    }

    @Override
    public boolean hasRegion(String world, int chunkX, int chunkZ, String regionId) {
        final World exactWorld = Bukkit.getWorld(world);
        if (this.isNotPresent() || exactWorld == null) return false;

        final ApplicableRegionSet regionSet = (ApplicableRegionSet) this.getRegionSetAt(world, chunkX, chunkZ);
        final Collection<ProtectedRegion> regions = regionSet.getRegions();

        if (regions != null && regionId != null) {
            return regions.contains(new ProtectedCuboidRegion(regionId, ZERO, ZERO));
        }
        return regions != null && !regions.isEmpty();
    }

    @Override
    public boolean hasRegion(String world, int chunkX, int chunkZ) {
        return this.hasRegion(world, chunkX, chunkZ, null);
    }

    @Override
    public boolean canPerformCustom(Player player, Location location, String flagName, boolean defaultValue) {
        if (this.isNotPresent()) return defaultValue;

        final ApplicableRegionSet regionSet = (ApplicableRegionSet) this.getRegionSetAt(location);
        final Flag<?> flag = this.instance.getFlagRegistry().get(checkNotNull(flagName));

        if (!(flag instanceof StateFlag)) {
            return defaultValue;
        }
        return regionSet.testState((LocalPlayer) this.getPlayer(player), (StateFlag) flag);
    }

    @Override
    public boolean canPerform(Player player, Location location, DefaultRegionFlag flag, boolean defaultValue) {
        final String replacement = flag.replacement;
        return this.canPerformCustom(player, location, replacement != null ? replacement : flag.name(), defaultValue);
    }

    @Override
    public boolean canPerformCustom(Player player, String world, int chunkX, int chunkZ, String flagName, boolean defaultValue) {
        if (this.isNotPresent()) return defaultValue;

        final ApplicableRegionSet regionSet = (ApplicableRegionSet) this.getRegionSetAt(world, chunkX, chunkZ);
        final Flag<?> flag = this.instance.getFlagRegistry().get(checkNotNull(flagName));

        if (!(flag instanceof StateFlag)) {
            return defaultValue;
        }
        return regionSet.testState((LocalPlayer) this.getPlayer(player), (StateFlag) flag);
    }

    @Override
    public boolean canPerform(Player player, String world, int chunkX, int chunkZ, DefaultRegionFlag flag, boolean defaultValue) {
        final String replacement = flag.replacement;
        return this.canPerformCustom(player, world, chunkX, chunkZ, replacement != null ? replacement : flag.name(), defaultValue);
    }

    @Override
    public boolean registerFlag(String name, boolean defaultValue) {
        final FlagRegistry registry = this.instance.getFlagRegistry();

        try {
            final Flag<?> flag = new StateFlag(name, defaultValue);
            registry.register(flag);
        } catch (FlagConflictException ignored) {
            return false;
        }

        return true;
    }

    /**
     * Query results off of WorldGuard 7's RegionManager.
     *
     * @param world {@link World} the world this query corresponds to.
     * @param query {@link Function} the query to be applied.
     * @param <T> {@link T} return type of query.
     * @return {@link T}
     */
    private <T> T query(final World world, final Function<RegionManager, T> query) {
        return query.apply(this.instance.getPlatform().getRegionContainer().get(adapt(world)));
    }

    /**
     * Get a new vector instance by location.
     *
     * @param location {@link Location} the location to parse into a vector.
     * @return {@link BlockVector3}
     */
    private BlockVector3 vectorAt(final Location location) {
        return at(location.getX(), location.getY(), location.getZ());
    }

    /**
     * Generate a new instance of this implementation.
     *
     * @return {@link WorldGuardLayer}
     */
    public static WorldGuardLayer<WorldGuard> generate() {
        return new Implementation(WorldGuard.getInstance());
    }
}