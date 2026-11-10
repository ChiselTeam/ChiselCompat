package io.github.chiselteam.chiselcompat.content.compat;

import io.github.chiselteam.chiselcompat.content.ChiselFamily;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

/** The supplier must stay lazy: disabled modules must never initialize their family classes. */
public record CompatModule(List<String> modIds, Supplier<List<ChiselFamily>> families) {
    public boolean isEnabled(boolean production, Predicate<String> isLoaded) {
        return !production || modIds.stream().anyMatch(isLoaded);
    }
}
