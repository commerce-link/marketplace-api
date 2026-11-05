package pl.commercelink.marketplace.api;

import java.math.BigDecimal;
import java.util.List;

/**
 * An offer as it currently exists on the marketplace.
 *
 * @param sellerOfferKey key the seller assigned to the offer; see {@link MarketplaceProduct#sellerOfferKey()}
 * @param eans           EAN codes of the offered product; never null
 */
public record MarketplaceListedOffer(
        String sellerOfferKey,
        List<String> eans,
        String title,
        long quantity,
        BigDecimal price,
        boolean active
) {

    public MarketplaceListedOffer {
        eans = eans == null ? List.of() : eans;
    }
}
