package adventofcode.interfaces;

import java.util.stream.Stream;

public interface ISolver<T> {
    T solve(Stream<String> input);
}
