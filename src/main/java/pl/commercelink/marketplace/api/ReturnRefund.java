package pl.commercelink.marketplace.api;

import java.util.List;

/** @param commandId client-generated idempotency key, stable across redeliveries of the same decision */
public record ReturnRefund(List<Item> items, boolean refundDelivery, String commandId) {

    public record Item(String manufacturerCode, int quantity) {
    }
}
