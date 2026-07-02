package pl.commercelink.marketplace.api;

import java.util.List;

public interface MarketplaceProvider {

    List<MarketplaceOrder> fetchOrders();

    void exportOffers(List<MarketplaceOffer> toPublish, List<MarketplaceOffer> toRemove);

    void acceptOrder(String externalOrderId);

    /**
     * Marks the order as shipped. All {@link ShipmentUpdate} fields may be null when the
     * shipment carries no tracking data (e.g. personal collection) — implementations must
     * skip tracking-specific calls but still mark the order as shipped.
     */
    void shipOrder(String externalOrderId, ShipmentUpdate update);

    void cancelOrder(String externalOrderId);

    default void completeOrder(String externalOrderId) {
    }

    void updateInvoice(String externalOrderId, InvoiceUpdate update);
}
