package pl.commercelink.marketplace.api;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MarketplaceProductTest {

    @Test
    void fiveArgumentConstructorLeavesSellerOfferKeyEmpty() {
        // given / when
        MarketplaceProduct product = new MarketplaceProduct("n", "M", BigDecimal.ONE, 1, BigDecimal.ZERO);

        // then
        assertNull(product.sellerOfferKey());
    }

    @Test
    void listedOfferTreatsMissingEansAsEmpty() {
        // given / when
        MarketplaceListedOffer offer = new MarketplaceListedOffer("K", null, "t", 0, BigDecimal.ONE, true);

        // then
        assertEquals(List.of(), offer.eans());
    }
}
