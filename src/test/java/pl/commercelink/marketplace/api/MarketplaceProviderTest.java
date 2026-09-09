package pl.commercelink.marketplace.api;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MarketplaceProviderTest {

    private static class RecordingProvider implements MarketplaceProvider {

        private List<MarketplaceOffer> publishedOffers;
        private List<MarketplaceOffer> removedOffers;

        @Override
        public List<MarketplaceOrder> fetchOrders() {
            return List.of();
        }

        @Override
        public void exportOffers(List<MarketplaceOffer> toPublish, List<MarketplaceOffer> toRemove) {
            this.publishedOffers = toPublish;
            this.removedOffers = toRemove;
        }

        @Override
        public void acceptOrder(String externalOrderId) {
        }

        @Override
        public void shipOrder(String externalOrderId, ShipmentUpdate update) {
        }

        @Override
        public void cancelOrder(String externalOrderId) {
        }

        @Override
        public void updateInvoice(String externalOrderId, InvoiceUpdate update) {
        }
    }

    private MarketplaceOffer offer(String productId) {
        return new MarketplaceOffer(productId, "5901234567890", "MC", "Acme", "Nazwa", "Kategoria", 149L, 10L, 3);
    }

    @Test
    void defaultReportingOverloadDelegatesToTwoArgumentVersion() {
        // given
        RecordingProvider provider = new RecordingProvider();
        List<MarketplaceOffer> toPublish = List.of(offer("PIM-1"));
        List<MarketplaceOffer> toRemove = List.of(offer("PIM-2"));

        // when
        provider.exportOffers(toPublish, toRemove, (productId, reasonCode, message) -> {
        });

        // then
        assertSame(toPublish, provider.publishedOffers);
        assertSame(toRemove, provider.removedOffers);
    }

    @Test
    void defaultReportingOverloadReportsNothing() {
        // given
        RecordingProvider provider = new RecordingProvider();
        List<String> reported = new ArrayList<>();

        // when
        provider.exportOffers(List.of(offer("PIM-1")), List.of(),
                (productId, reasonCode, message) -> reported.add(reasonCode));

        // then
        assertTrue(reported.isEmpty());
        assertEquals(1, provider.publishedOffers.size());
    }

    @Test
    void returnsAreAbsentByDefault() {
        // when / then
        assertTrue(new RecordingProvider().returns().isEmpty());
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
