package pl.commercelink.marketplace.api;

/**
 * Constant NAMES are persisted by consumers (the app stores them on RMA.externalReturnStatus). Renaming or
 * removing a constant is a breaking change; adding one requires that consumers are never rolled back past
 * the release that introduced it.
 */
public enum MarketplaceReturnStatus {
    DECLARED,
    IN_TRANSIT,
    DELIVERED,
    REFUNDED,
    REJECTED;

    public boolean isClosed() {
        return this == REFUNDED || this == REJECTED;
    }
}
