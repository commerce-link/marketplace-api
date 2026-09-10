package pl.commercelink.marketplace.api;

import java.util.List;

public interface MarketplaceReturns {

    /**
     * Returns declared by buyers: every open return plus returns closed recently enough for the
     * app to reconcile refunds it did not perform itself (window is implementation-defined,
     * at least 30 days). Stateless, like {@link MarketplaceProvider#fetchOrders()}; the app
     * deduplicates by {@link MarketplaceReturn#externalReturnId()}.
     */
    List<MarketplaceReturn> fetchReturns();

    /**
     * Refunds the buyer for the returned items; see {@link ReturnRefund} for the partial-refund and
     * idempotency contract. Any exception is retried by the app (SQS redelivery, then DLQ) — including
     * 4xx errors that a retry cannot fix, because the app does not yet distinguish terminal from transient
     * failures. Throw before any money-moving call when the request cannot be built.
     */
    void refundReturn(String externalOrderId, String externalReturnId, ReturnRefund refund);

    /**
     * Rejects the return with a reason shown to the buyer. Must be idempotent: gate on the live state of
     * the return on the marketplace (already rejected / already refunded is a no-op), never on cached state.
     */
    void rejectReturn(String externalReturnId, ReturnRejection rejection);
}
