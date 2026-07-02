package pl.commercelink.marketplace.api;

import java.util.List;

public interface MarketplaceProvider {

    List<MarketplaceOrder> fetchOrders();

    void exportOffers(List<MarketplaceOffer> toPublish, List<MarketplaceOffer> toRemove);

    void acceptOrder(String externalOrderId);

    void shipOrder(String externalOrderId, ShipmentUpdate update);

    void cancelOrder(String externalOrderId);

    default void completeOrder(String externalOrderId) {
    }

    void updateInvoice(String externalOrderId, InvoiceUpdate update);
}
