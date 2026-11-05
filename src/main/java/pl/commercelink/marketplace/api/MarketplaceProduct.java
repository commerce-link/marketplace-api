package pl.commercelink.marketplace.api;

import java.math.BigDecimal;

/**
 * One order line.
 *
 * @param sellerOfferKey key the seller assigned to the offer, stable across the offer's lifetime
 *                       (Empik {@code offer_sku}, Allegro {@code external.id}, CS-Cart
 *                       {@code product_code}); null when the marketplace does not provide it.
 *                       Not the same as {@link MarketplaceReturn.Item#offerKey()}.
 */
public record MarketplaceProduct(
        String name,
        String manufacturerCode,
        BigDecimal priceGross,
        long quantity,
        BigDecimal commission,
        String sellerOfferKey
) {

    public MarketplaceProduct(String name, String manufacturerCode, BigDecimal priceGross, long quantity,
                              BigDecimal commission) {
        this(name, manufacturerCode, priceGross, quantity, commission, null);
    }
}
