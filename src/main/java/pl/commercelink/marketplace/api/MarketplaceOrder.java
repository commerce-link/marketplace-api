package pl.commercelink.marketplace.api;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record MarketplaceOrder(
        String externalOrderId,
        String externalSupplierId,
        MarketplaceCustomer customer,
        List<MarketplaceProduct> products,
        Shipping shipping,
        String paymentType,
        String paymentTransactionId
) {

    public MarketplaceOrder(
            String externalOrderId,
            MarketplaceCustomer customer,
            List<MarketplaceProduct> products,
            Shipping shipping,
            String paymentType,
            String paymentTransactionId
    ) {
        this(externalOrderId, null, customer, products, shipping, paymentType, paymentTransactionId);
    }

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
