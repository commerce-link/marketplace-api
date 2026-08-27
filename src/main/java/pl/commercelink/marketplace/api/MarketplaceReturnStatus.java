package pl.commercelink.marketplace.api;

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
