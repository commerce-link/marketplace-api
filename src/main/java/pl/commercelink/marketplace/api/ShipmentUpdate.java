package pl.commercelink.marketplace.api;

public record ShipmentUpdate(
        String trackingNo,
        String carrierId,
        String carrierName,
        String trackingUrl
) {
}
