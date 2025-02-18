package xyz.jpenilla.squaremap.plugin.data;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;
import xyz.jpenilla.squaremap.plugin.util.Numbers;

@DefaultQualifier(NonNull.class)
public record ChunkCoordinate(int x, int z) {

    public int getRegionX() {
        return Numbers.chunkToRegion(this.x);
    }

    public int getRegionZ() {
        return Numbers.chunkToRegion(this.z);
    }

    public int getBlockX() {
        return Numbers.chunkToBlock(this.x);
    }

    public int getBlockZ() {
        return Numbers.chunkToBlock(this.z);
    }

    public RegionCoordinate regionCoordinate() {
        return new RegionCoordinate(this.getRegionX(), this.getRegionZ());
    }
}
