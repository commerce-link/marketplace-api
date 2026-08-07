package pl.commercelink.marketplace.api;

public record MarketplaceCustomer(
        CustomerType customerType,
        String name,
        String company,
        String email,
        String phone,
        String taxId,
        Address billingAddress,
        Address shippingAddress
) {

    public enum CustomerType {
        INDIVIDUAL, COMPANY
    }

    public record Address(
            String name,
            String phone,
            String street,
            String postalCode,
            String city,
            String country
    ) {
    }
}
