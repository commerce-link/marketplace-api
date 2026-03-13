package pl.commercelink.marketplace.api;

public record ShipmentUpdate(
        String trackingNo,
        String carrier,
        String trackingUrl
) {
}
