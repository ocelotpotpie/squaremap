package xyz.jpenilla.squaremap.plugin.api;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.checkerframework.checker.nullness.qual.NonNull;
import xyz.jpenilla.squaremap.api.Key;
import xyz.jpenilla.squaremap.api.LayerProvider;
import xyz.jpenilla.squaremap.api.Pair;
import xyz.jpenilla.squaremap.api.Registry;

public final class LayerRegistry implements Registry<LayerProvider> {

    private final Map<Key, LayerProvider> layerProviders = new ConcurrentHashMap<>();

    @Override
    public void register(@NonNull Key key, @NonNull LayerProvider value) {
        if (this.hasEntry(key)) {
            throw layerAlreadyRegistered(key);
        }
        this.layerProviders.put(key, value);
    }

    @Override
    public void unregister(@NonNull Key key) {
        final LayerProvider removed = this.layerProviders.remove(key);
        if (removed == null) {
            throw noLayerRegistered(key);
        }
    }

    @Override
    public boolean hasEntry(@NonNull Key key) {
        return this.layerProviders.containsKey(key);
    }

    @Override
    public @NonNull LayerProvider get(@NonNull Key key) {
        final LayerProvider provider = this.layerProviders.get(key);
        if (provider == null) {
            throw noLayerRegistered(key);
        }
        return provider;
    }

    @Override
    public @NonNull Iterable<Pair<Key, LayerProvider>> entries() {
        return this.layerProviders.entrySet().stream()
            .map(entry -> Pair.of(entry.getKey(), entry.getValue()))
            .toList();
    }

    private static @NonNull IllegalArgumentException noLayerRegistered(final @NonNull Key key) {
        return new IllegalArgumentException(String.format("No LayerProvider registered for key '%s'", key.getKey()));
    }

    private static @NonNull IllegalArgumentException layerAlreadyRegistered(final @NonNull Key key) {
        throw new IllegalArgumentException(String.format("LayerProvider already registered for key '%s'", key.getKey()));
    }

}
