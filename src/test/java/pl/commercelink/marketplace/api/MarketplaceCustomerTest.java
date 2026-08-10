package pl.commercelink.marketplace.api;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MarketplaceCustomerTest {

    private MarketplaceOrder orderWith(PickupPoint pickupPoint) {
        return new MarketplaceOrder("o-1", null, List.of(), BigDecimal.ZERO,
                "DirectDebit", "tx-1", "INPOST", pickupPoint);
    }

    @Test
    void theOrderCarriesTheChosenPickupPoint() {
        // when
        MarketplaceOrder order = orderWith(new PickupPoint("KRA01M"));

        // then
        assertEquals("KRA01M", order.pickupPoint().code());
        assertEquals("INPOST", order.deliveryCarrier());
    }

    @Test
    void shortConstructorLeavesCarrierAndPointUnset() {
        // when
        MarketplaceOrder order = new MarketplaceOrder(
                "o-2", null, List.of(), BigDecimal.ZERO, "DirectDebit", "tx-2");

        // then
        assertNull(order.deliveryCarrier());
        assertNull(order.pickupPoint());
    }
}
