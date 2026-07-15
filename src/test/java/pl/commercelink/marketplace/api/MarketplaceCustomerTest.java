package pl.commercelink.marketplace.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MarketplaceCustomerTest {

    @Test
    void sixArgAddressConstructorDefaultsPickupPointToNull() {
        // given / when
        MarketplaceCustomer.Address address = new MarketplaceCustomer.Address(
                "Jan Kowalski", "+48123123123", "Prosta 1", "00-001", "Warszawa", "PL");

        // then
        assertNull(address.pickupPoint());
    }

    @Test
    void addressCarriesStructuredPickupPoint() {
        // given / when
        MarketplaceCustomer.Address address = new MarketplaceCustomer.Address(
                "Jan Kowalski", "+48123123123", "Prosta 1", "00-001", "Warszawa", "PL",
                new MarketplaceCustomer.PickupPoint("ALP123", "Paczkomat ALP123"));

        // then
        assertEquals("ALP123", address.pickupPoint().id());
        assertEquals("Paczkomat ALP123", address.pickupPoint().name());
    }
}
