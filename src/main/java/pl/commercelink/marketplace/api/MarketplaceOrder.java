package pl.commercelink.marketplace.api;

import java.math.BigDecimal;
import java.util.List;

public record MarketplaceOrder<C extends Enum<C>>(
        String externalOrderId,
        MarketplaceCustomer customer,
        List<MarketplaceProduct> products,
        BigDecimal shippingCost,
        String paymentType,
        String paymentTransactionId,
        C deliveryCarrier
) {

    public MarketplaceOrder(
            String externalOrderId,
            MarketplaceCustomer customer,
            List<MarketplaceProduct> products,
            BigDecimal shippingCost,
            String paymentType,
            String paymentTransactionId) {
        this(externalOrderId, customer, products, shippingCost, paymentType, paymentTransactionId, null);
    }
}
