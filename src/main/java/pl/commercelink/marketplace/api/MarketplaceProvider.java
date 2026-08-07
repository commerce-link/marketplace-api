package pl.commercelink.marketplace.api;

import java.util.List;

public interface MarketplaceProvider<C extends Enum<C>> {

    List<MarketplaceOrder<C>> fetchOrders();

    void exportOffers(List<MarketplaceOffer> toPublish, List<MarketplaceOffer> toRemove);

    /**
     * Accepts the order on the marketplace. Implementations must be idempotent: the same order
     * may be accepted more than once, because lifecycle events are delivered at-least-once and
     * the caller may re-issue an accept for an order it has already accepted. A repeated accept
     * must therefore be a no-op rather than an error. Where the marketplace rejects a repeated
     * accept, gate the call on the order's live state on the marketplace — never on locally
     * cached state, which goes stale as soon as the order is changed on the marketplace panel.
     */
    void acceptOrder(String externalOrderId);

    /**
     * Marks the order as shipped. All {@link ShipmentUpdate} fields may be null when the
     * shipment carries no tracking data (e.g. personal collection) — implementations must
     * skip tracking-specific calls in that case. Whether the order is still marked as
     * shipped without a tracking number is left to the implementation, as some marketplaces
     * reject a ship transition that has no registered tracking number.
     */
    void shipOrder(String externalOrderId, ShipmentUpdate update);

    void cancelOrder(String externalOrderId);

    default void completeOrder(String externalOrderId) {
    }

    void updateInvoice(String externalOrderId, InvoiceUpdate update);
}
