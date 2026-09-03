package pl.commercelink.marketplace.api;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record MarketplaceOrder(
        String externalOrderId,
        MarketplaceCustomer customer,
        List<MarketplaceProduct> products,
        Shipping shipping,
        String paymentType,
        String paymentTransactionId
) {

    public record Shipping(
            BigDecimal cost,
            String carrier,
            PickupPoint pickupPoint,
            LocalDate estimatedShippingAt
    ) {
        public static Shipping of(BigDecimal cost) {
            return new Shipping(cost, null, null, null);
        }
    }
}
