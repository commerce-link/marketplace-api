package pl.commercelink.marketplace.api;

import java.util.List;

/**
 * @param commandId client-generated idempotency key, stable across redeliveries of the same decision
 * @param referenceNumber the return's buyer-facing reference number (e.g. "XGQX/2026"); null when unavailable,
 *                         in which case callers should fall back to the internal return id
 */
public record ReturnRefund(List<Item> items, boolean refundDelivery, String commandId, String referenceNumber) {

    public record Item(String manufacturerCode, int quantity) {
    }
}
