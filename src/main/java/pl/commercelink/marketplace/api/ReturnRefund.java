package pl.commercelink.marketplace.api;

import java.util.List;

/**
 * One (possibly partial) refund for a return. The app calls {@link MarketplaceReturns#refundReturn} once per
 * accepted batch of items, so a single return may receive several refunds whose quantities add up on the
 * marketplace side; {@code refundDelivery} may be true in more than one of them.
 *
 * @param items          non-empty for a refund of goods; the adapter must not POST an empty refund
 * @param idempotencyKey client-generated key, stable across redeliveries of the same decision. A marketplace
 *                       without native idempotency MUST gate the refund on the live state of the return
 *                       instead (as {@link MarketplaceReturns#rejectReturn} does) — never refund twice.
 */
public record ReturnRefund(List<Item> items, boolean refundDelivery, String idempotencyKey) {

    public ReturnRefund {
        items = items == null ? List.of() : items;
    }

    /** @param offerKey the value of {@link MarketplaceReturn.Item#offerKey()} handed back verbatim. */
    public record Item(String offerKey, int quantity) {
    }
}
