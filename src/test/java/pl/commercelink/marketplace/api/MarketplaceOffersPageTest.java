package pl.commercelink.marketplace.api;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MarketplaceOffersPageTest {

    @Test
    void offersPageTreatsMissingOffersAsEmpty() {
        // given / when
        MarketplaceOffersPage page = new MarketplaceOffersPage(null, null, null);

        // then
        assertEquals(List.of(), page.offers());
    }

    @Test
    void lastPageHasNoNextCursor() {
        // given / when
        MarketplaceOffersPage page = new MarketplaceOffersPage(List.of(), null, 0);

        // then
        assertNull(page.nextCursor());
        assertEquals(0, page.total());
    }

    @Test
    void rateLimitedExceptionCarriesRetryAfter() {
        // given / when
        MarketplaceRateLimitedException exception = new MarketplaceRateLimitedException(Duration.ofSeconds(60));

        // then
        assertEquals(Duration.ofSeconds(60), exception.retryAfter());
        assertThrows(IllegalArgumentException.class, () -> new MarketplaceRateLimitedException(Duration.ZERO));
        assertThrows(IllegalArgumentException.class, () -> new MarketplaceRateLimitedException(null));
    }
}
