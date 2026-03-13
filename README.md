# Marketplace API

This library defines a provider-agnostic API for integrating external marketplace systems into the CommerceLink platform. It provides a common set of interfaces and data models that marketplace provider implementations must adhere to, enabling seamless swapping or coexistence of different marketplace backends.

The core `MarketplaceProvider` interface supports order fetching, offer export, order status updates, shipment updates, and invoice updates.

## Provider Discovery

This library extends the [provider-api](https://github.com/commerce-link/provider-api) plugin system. The `MarketplaceProviderDescriptor` interface extends `ProviderDescriptor<MarketplaceProvider>` and serves as the SPI entry point for pluggable marketplace implementations.

Concrete implementations are discovered at runtime via `ServiceLoader`. See the [provider-api README](https://github.com/commerce-link/provider-api) for registration details.
