package pl.commercelink.marketplace.api;

public record MarketplaceOffer(
        String productId,
        String ean,
        String manufacturerCode,
        String brand,
        String name,
        String categoryName,
        long price,
        long quantity,
        int estimatedDeliveryDays
) {
}
