package xyz.jpenilla.squaremap.plugin.data;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;
import xyz.jpenilla.squaremap.plugin.util.Numbers;

@DefaultQualifier(NonNull.class)
public record RegionCoordinate(int x, int z) {

    public int getChunkX() {
        return Numbers.regionToChunk(this.x);
    }

    public int getChunkZ() {
        return Numbers.regionToChunk(this.z);
    }

    public int getBlockX() {
        return Numbers.regionToBlock(this.x);
    }

    public int getBlockZ() {
        return Numbers.regionToBlock(this.z);
    }

    public ChunkCoordinate chunkCoordinate() {
        return new ChunkCoordinate(this.getChunkX(), this.getChunkZ());
    }
}
