package adventofcode.interfaces;

import java.util.List;

public interface ISolver<T> {
    T solve(List<String> input);
}
