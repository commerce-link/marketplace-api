package pl.commercelink.marketplace.api;

import java.util.Arrays;
import java.util.List;

public interface AliasedCarrier {

    List<String> aliases();

    static <E extends Enum<E> & AliasedCarrier> E deserialize(E[] values, String code) {
        if (code == null || code.isBlank()) {
            return null;
        }
        return Arrays.stream(values)
                .filter(carrier -> matchesExactly(carrier, code))
                .findFirst()
                .orElseGet(() -> Arrays.stream(values)
                        .filter(carrier -> contains(carrier, code))
                        .findFirst()
                        .orElse(null));
    }

    private static <E extends Enum<E> & AliasedCarrier> boolean matchesExactly(E carrier, String input) {
        String normalized = input.trim();
        return normalized.equalsIgnoreCase(carrier.name())
                || carrier.aliases().stream().anyMatch(alias -> alias.equalsIgnoreCase(normalized));
    }

    private static <E extends Enum<E> & AliasedCarrier> boolean contains(E carrier, String input) {
        String normalized = input.trim().toUpperCase();
        String name = carrier.name();
        return normalized.contains(name.replace('_', ' '))
                || normalized.contains(name)
                || carrier.aliases().stream().anyMatch(alias -> normalized.contains(alias.toUpperCase()));
    }
}
