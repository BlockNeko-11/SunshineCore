package io.github.blockneko11.sunshinecore.util;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

public final class CollectionUtils {
    public static <K, V> void toMutableMap(Supplier<Map<K, V>> getter, Consumer<Map<K, V>> setter) {
        Map<K, V> base = getter.get();
        if (!(base instanceof HashMap<K,V>)) {
            setter.accept(new HashMap<>(base));
        }
    }

    public static <E> void toMutableSet(Supplier<Set<E>> getter, Consumer<Set<E>> setter) {
        Set<E> base = getter.get();
        if (!(base instanceof HashSet<E>)) {
            setter.accept(new HashSet<>(base));
        }
    }

    public static <E> void toMutableList(Supplier<List<E>> getter, Consumer<List<E>> setter) {
        List<E> base = getter.get();
        if (!(base instanceof ArrayList<E>)) {
            setter.accept(new ArrayList<>(base));
        }
    }

    private CollectionUtils() {
    }
}
