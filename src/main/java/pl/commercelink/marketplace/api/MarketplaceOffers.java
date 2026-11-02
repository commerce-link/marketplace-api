package pl.commercelink.marketplace.api;

/**
 * Read-only listing of the offers the seller currently has on the marketplace, fetched one page
 * per call so that the caller decides when to ask for the next one (and can resume later).
 * <p>
 * Each call performs exactly one request to the marketplace. When the marketplace throttles the
 * caller, the implementation throws {@link MarketplaceRateLimitedException} instead of waiting,
 * so the caller can schedule the retry itself.
 */
public interface MarketplaceOffers {

    /**
     * @param cursor opaque position returned as {@link MarketplaceOffersPage#nextCursor()} by the previous call;
     *               null requests the first page
     * @throws MarketplaceRateLimitedException if the marketplace asks the caller to slow down
     */
    MarketplaceOffersPage page(String cursor);
}
