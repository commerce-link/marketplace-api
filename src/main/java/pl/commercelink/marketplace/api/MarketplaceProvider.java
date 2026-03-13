package pl.commercelink.marketplace.api;

import java.util.List;

public interface MarketplaceProvider {

    String name();

    List<MarketplaceOrder> fetchNewOrders();

    void exportOffers(List<MarketplaceOffer> toPublish, List<MarketplaceOffer> toRemove);

    void updateOrderStatus(String externalOrderId, MarketplaceOrderStatus status);

    void updateShipment(String externalOrderId, ShipmentUpdate update);

    void updateInvoice(String externalOrderId, InvoiceUpdate update);
}
