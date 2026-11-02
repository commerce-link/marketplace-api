package pl.commercelink.marketplace.api;

import java.util.List;

/**
 * One page of the seller's offers.
 *
 * @param offers     offers on this page; never null
 * @param nextCursor cursor to pass to {@link MarketplaceOffers#page(String)} for the following page; null when this is the last page
 * @param total      total number of offers the marketplace reports, or null if it does not say
 */
public record MarketplaceOffersPage(
        List<MarketplaceListedOffer> offers,
        String nextCursor,
        Integer total
) {

    public MarketplaceOffersPage {
        offers = offers == null ? List.of() : offers;
    }
}
