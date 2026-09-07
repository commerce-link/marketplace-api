package pl.commercelink.marketplace.api;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MarketplaceOrderTest {

    @Test
    void ordersFromMarketplacesWithoutSupplierRoutingCarryNoExternalSupplierId() {
        MarketplaceOrder order = new MarketplaceOrder(
                "98", null, List.of(), MarketplaceOrder.Shipping.of(BigDecimal.ZERO), "DirectDebit", null);

        assertNull(order.externalSupplierId());
        assertEquals("98", order.externalOrderId());
    }

    @Test
    void ordersFromMarketplacesRoutingToASupplierCarryItsExternalId() {
        MarketplaceOrder order = new MarketplaceOrder(
                "98", "2", null, List.of(), MarketplaceOrder.Shipping.of(BigDecimal.ZERO), "DirectDebit", null);

        assertEquals("2", order.externalSupplierId());
    }
}
