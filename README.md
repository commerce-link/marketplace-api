# Marketplace API

This library defines a provider-agnostic API for integrating external marketplace systems into the CommerceLink platform. It provides a common set of interfaces and data models that marketplace provider implementations must adhere to, enabling seamless swapping or coexistence of different marketplace backends.

The core `MarketplaceProvider` interface supports order fetching, offer export, order status updates, shipment updates, and invoice updates.

## Provider Discovery

This library extends the [provider-api](https://github.com/commerce-link/provider-api) plugin system. The `MarketplaceProviderDescriptor` interface extends `ProviderDescriptor<MarketplaceProvider>` and serves as the SPI entry point for pluggable marketplace implementations.

Concrete implementations are discovered at runtime via `ServiceLoader`. See the [provider-api README](https://github.com/commerce-link/provider-api) for registration details.

## Customer returns (0.5.0)

`MarketplaceProvider.returns()` returns `Optional<MarketplaceReturns>`; the default is empty, meaning
the marketplace has no returns API and the app skips both return import and return decisions for it.
`MarketplaceReturns` = `fetchReturns()` (stateless poll), `refundReturn(...)` (idempotent by
`ReturnRefund.commandId`), `rejectReturn(...)` (idempotent by live state). `MarketplaceReturn.Item.manufacturerCode`
uses the same key as `MarketplaceProduct.manufacturerCode` so the app can match order items.

### MarketplaceReturnStatus

Consumers persist these constant names (the app stores them on `RMA.externalReturnStatus` via
`@DynamoDBTypeConvertedEnum`). Renaming or removing a constant is a breaking change — bump the major
version, and never roll an app back past a release that introduced a new constant: a stored string with
no matching constant throws on read, and because the returns lookup queries the whole store partition,
one bad record breaks both returns import and the RMA search for that entire store.
