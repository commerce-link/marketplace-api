package pl.commercelink.marketplace.api;

import java.time.Duration;

/**
 * The marketplace refused a request because the caller exceeded its rate limit.
 * Retrying after {@link #retryAfter()} is expected to succeed.
 */
public class MarketplaceRateLimitedException extends RuntimeException {

    private final Duration retryAfter;

    public MarketplaceRateLimitedException(Duration retryAfter) {
        super("Marketplace rate limit hit, retry after " + retryAfter);
        if (retryAfter == null || retryAfter.isZero() || retryAfter.isNegative()) {
            throw new IllegalArgumentException("retryAfter must be positive");
        }
        this.retryAfter = retryAfter;
    }

    public Duration retryAfter() {
        return retryAfter;
    }
}
