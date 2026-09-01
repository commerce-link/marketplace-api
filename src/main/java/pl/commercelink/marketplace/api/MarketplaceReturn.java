package pl.commercelink.marketplace.api;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record MarketplaceReturn(
        String externalReturnId,
        String externalOrderId,
        String referenceNumber,
        MarketplaceReturnStatus status,
        LocalDateTime createdAt,
        List<Item> items,
        List<Parcel> parcels
) {

    public MarketplaceReturn {
        items = items == null ? List.of() : items;
        parcels = parcels == null ? List.of() : parcels;
    }

    /** manufacturerCode uses the same key as {@link MarketplaceProduct#manufacturerCode()} at order import. */
    public record Item(String manufacturerCode, int quantity, BigDecimal unitPriceGross, String reason) {
    }

    public record Parcel(String trackingNo, String carrierId) {
    }
}
