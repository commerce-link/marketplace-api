package pl.commercelink.marketplace.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MarketplaceCustomerTest {

    private MarketplaceOrder orderWith(PickupPoint pickupPoint) {
        return new MarketplaceOrder("o-1", null, List.of(),
                new MarketplaceOrder.Shipping(BigDecimal.ZERO, "INPOST", pickupPoint, LocalDate.of(2026, 9, 3)),
                new MarketplaceOrder.Payment("DirectDebit", "tx-1"));
    }

    @Test
    @DisplayName("the order carries the chosen pickup point and its carrier")
    void theOrderCarriesTheChosenPickupPoint() {
        MarketplaceOrder order = orderWith(new PickupPoint("KRA01M"));

        assertEquals("KRA01M", order.shipping().pickupPoint().code());
        assertEquals("INPOST", order.shipping().carrier());
        assertEquals(LocalDate.of(2026, 9, 3), order.shipping().estimatedShippingAt());
    }

    @Test
    @DisplayName("a marketplace supplying only the shipping cost leaves the rest unset")
    void shippingOfCostLeavesTheRestUnset() {
        MarketplaceOrder order = new MarketplaceOrder("o-2", null, List.of(),
                MarketplaceOrder.Shipping.of(BigDecimal.ZERO),
                new MarketplaceOrder.Payment("DirectDebit", "tx-2"));

        assertEquals(BigDecimal.ZERO, order.shipping().cost());
        assertNull(order.shipping().carrier());
        assertNull(order.shipping().pickupPoint());
        assertNull(order.shipping().estimatedShippingAt());
    }
}
