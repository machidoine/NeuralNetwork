package ben.stream;

import com.google.common.collect.Streams;

import java.util.AbstractMap;
import java.util.stream.Stream;

public class StreamUtils {
    public static <A> A  zipReduce(Stream<A> s1, Stream<A> s2, A identity, TriFunction<A, A, A, A> myF) {
        return Streams.zip(s1, s2, AbstractMap.SimpleEntry::new)
                .reduce(newSingleEntry(identity), (i, v) -> (newSingleEntry(myF.apply(i.getKey(), v.getKey(), v.getValue())))).getKey();
    }

    public static <A, B> AbstractMap.SimpleEntry<A, B> newSingleEntry(A key) {
        return new AbstractMap.SimpleEntry<>(key, null);
    }
}
