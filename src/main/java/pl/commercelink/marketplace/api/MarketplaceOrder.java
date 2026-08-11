package pl.commercelink.marketplace.api;

import java.math.BigDecimal;
import java.util.List;

public record MarketplaceOrder(
        String externalOrderId,
        MarketplaceCustomer customer,
        List<MarketplaceProduct> products,
        BigDecimal shippingCost,
        String shippingCarrier,
        String paymentType,
        String paymentTransactionId,
        PickupPoint pickupPoint
) {

    public MarketplaceOrder(
            String externalOrderId,
            MarketplaceCustomer customer,
            List<MarketplaceProduct> products,
            BigDecimal shippingCost,
            String paymentType,
            String paymentTransactionId) {
        this(externalOrderId, customer, products, shippingCost, null, paymentType, paymentTransactionId, null);
    }
}
