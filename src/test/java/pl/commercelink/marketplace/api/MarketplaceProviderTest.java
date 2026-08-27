package pl.commercelink.marketplace.api;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MarketplaceProviderTest {

    private static final MarketplaceProvider PROVIDER_WITHOUT_RETURNS = new MarketplaceProvider() {
        @Override public List<MarketplaceOrder> fetchOrders() { return List.of(); }
        @Override public void exportOffers(List<MarketplaceOffer> toPublish, List<MarketplaceOffer> toRemove) { }
        @Override public void acceptOrder(String externalOrderId) { }
        @Override public void shipOrder(String externalOrderId, ShipmentUpdate update) { }
        @Override public void cancelOrder(String externalOrderId) { }
        @Override public void updateInvoice(String externalOrderId, InvoiceUpdate update) { }
    };

    @Test
    void returnsAreAbsentByDefault() {
        // when / then
        assertTrue(PROVIDER_WITHOUT_RETURNS.returns().isEmpty());
    }

    @Test
    void refundedAndRejectedStatusesAreClosed() {
        // when / then
        assertTrue(MarketplaceReturnStatus.REFUNDED.isClosed());
        assertTrue(MarketplaceReturnStatus.REJECTED.isClosed());
        assertFalse(MarketplaceReturnStatus.DECLARED.isClosed());
        assertFalse(MarketplaceReturnStatus.IN_TRANSIT.isClosed());
        assertFalse(MarketplaceReturnStatus.DELIVERED.isClosed());
    }
}
