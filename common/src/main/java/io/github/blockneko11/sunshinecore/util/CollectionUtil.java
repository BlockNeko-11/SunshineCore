package io.github.blockneko11.sunshinecore.util;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public final class CollectionUtil {
    public static <K, V> void toMutable(Supplier<Map<K, V>> getter, Consumer<Map<K, V>> setter) {
        Map<K, V> base = getter.get();
        if (!(base instanceof HashMap<K,V>)) {
            setter.accept(new HashMap<>(base));
        }
    }

    private CollectionUtil() {
    }
}
