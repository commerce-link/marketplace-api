package pl.commercelink.marketplace.api;

import java.util.List;
import java.util.function.Consumer;

/**
 * Read-only listing of the offers the seller currently has on the marketplace, delivered page
 * by page so that large accounts are never held in memory at once.
 */
public interface MarketplaceOffers {

    void forEachPage(Consumer<List<MarketplaceListedOffer>> page);
}
