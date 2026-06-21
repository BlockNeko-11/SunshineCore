package io.github.blockneko11.sunshinecore.util;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public final class StreamUtils {
    public static <T> Stream<T> toStream(Iterable<T> it) {
        return StreamSupport.stream(it.spliterator(), false);
    }

    private StreamUtils() {
    }
}
