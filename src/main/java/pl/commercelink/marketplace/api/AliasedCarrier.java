package pl.commercelink.marketplace.api;

import java.util.Arrays;
import java.util.List;

public interface AliasedCarrier {

    List<String> aliases();

    default boolean matchesExactly(String input) {
        String normalized = input.trim();
        return normalized.equalsIgnoreCase(((Enum<?>) this).name())
                || aliases().stream().anyMatch(alias -> alias.equalsIgnoreCase(normalized));
    }

    default boolean contains(String input) {
        String normalized = input.trim().toUpperCase();
        String name = ((Enum<?>) this).name();
        return normalized.contains(name.replace('_', ' '))
                || normalized.contains(name)
                || aliases().stream().anyMatch(alias -> normalized.contains(alias.toUpperCase()));
    }

    static <E extends Enum<E> & AliasedCarrier> E deserialize(E[] values, String code) {
        if (code == null || code.isBlank()) {
            return null;
        }
        return Arrays.stream(values)
                .filter(carrier -> carrier.matchesExactly(code))
                .findFirst()
                .orElseGet(() -> Arrays.stream(values)
                        .filter(carrier -> carrier.contains(code))
                        .findFirst()
                        .orElse(null));
    }
}
