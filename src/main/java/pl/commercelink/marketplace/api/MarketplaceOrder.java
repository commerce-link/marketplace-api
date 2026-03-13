package pl.commercelink.marketplace.api;

import java.math.BigDecimal;
import java.util.List;

public record MarketplaceOrder(
        String externalOrderId,
        MarketplaceCustomer customer,
        List<MarketplaceProduct> products,
        BigDecimal shippingCost,
        String paymentType,
        String paymentTransactionId
) {
}
