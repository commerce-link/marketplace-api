package pl.commercelink.marketplace.api;

import java.time.LocalDateTime;
import java.util.List;

/**
 * One customer return as seen on the marketplace.
 *
 * <p>{@code externalReturnId}, {@code externalOrderId} and {@code status} are required. {@code createdAt}
 * may be null (the app then uses the import time). {@code items} drive the matching against order items;
 * {@code parcels} become the RMA's shipments and are filled in on the first poll on which they appear
 * (later changes to parcels are ignored). {@code Parcel.carrierId} is the marketplace's own carrier
 * identifier, stored on the shipment without translation.
 *
 * <p>{@code referenceNumber} is the marketplace's own buyer-facing reference for the return (Allegro:
 * "XGQX/2026"); null when the marketplace has none, in which case consumers fall back to
 * {@code externalReturnId}. It is shown to operators and must never be the internal return id.
 */
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

    /**
     * @param offerKey the offer key the marketplace used at order import (same value as
     *                 {@link MarketplaceProduct#manufacturerCode()}); opaque to the app, which hands it back
     *                 verbatim in {@link ReturnRefund.Item#offerKey()}. Adapters must also accept the
     *                 normalised variant (upper-case, whitespace removed) for orders imported before the app
     *                 started persisting the raw key.
     */
    public record Item(String offerKey, int quantity, String reason) {
    }

    public record Parcel(String trackingNo, String carrierId) {
    }
}
