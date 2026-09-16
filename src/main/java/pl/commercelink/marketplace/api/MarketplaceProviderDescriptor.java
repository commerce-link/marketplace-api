package pl.commercelink.marketplace.api;

import pl.commercelink.provider.api.ProviderDescriptor;

public interface MarketplaceProviderDescriptor extends ProviderDescriptor<MarketplaceProvider> {

    default boolean supportsReturns() {
        return false;
    }
}
