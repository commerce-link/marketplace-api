package pl.commercelink.marketplace.api;

import java.math.BigDecimal;

public record MarketplaceProduct(
        String name,
        String manufacturerCode,
        BigDecimal priceGross,
        long quantity,
        BigDecimal commission
) {
}
