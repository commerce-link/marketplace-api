package pl.commercelink.marketplace.api;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MarketplaceReturnTest {

    @Test
    void normalisesNullCollectionsToEmptyLists() {
        // given / when
        MarketplaceReturn ret = new MarketplaceReturn("r-1", "o-1", "REF", MarketplaceReturnStatus.DECLARED,
                LocalDateTime.now(), null, null);

        // then: consumers iterate these without a null check
        assertTrue(ret.items().isEmpty());
        assertTrue(ret.parcels().isEmpty());
    }
}
