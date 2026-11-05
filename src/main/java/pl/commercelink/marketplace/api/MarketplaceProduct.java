package pl.commercelink.marketplace.api;

import java.math.BigDecimal;

/**
 * One order line.
 *
 * @param sellerOfferKey key the seller assigned to the offer
 *                       (Empik {@code offer_sku}, Allegro {@code external.id} or the offer id when the
 *                       seller set none, CS-Cart {@code product_code}); null when the marketplace does not provide it.
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
