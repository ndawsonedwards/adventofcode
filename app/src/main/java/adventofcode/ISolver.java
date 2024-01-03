package adventofcode;

import java.util.stream.Stream;

public interface ISolver<T> {
    T solve(Stream<String> input);
}
