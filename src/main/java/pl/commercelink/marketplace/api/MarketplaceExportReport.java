package pl.commercelink.marketplace.api;

@FunctionalInterface
public interface MarketplaceExportReport {

    void rejected(String productId, String reasonCode, String message);
}
