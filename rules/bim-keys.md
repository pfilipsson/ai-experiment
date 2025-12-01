# Business Identifier Rules (Generic BIM Keys)

This file describes general rules for business identifiers used in APIs.
These are **generic** and **not tied to any internal system**.

## Identifier Requirements
- Identifiers must be stable and non-changing.
- Must be unique within their domain (e.g., account, user, transaction).
- Should be opaque (no meaning encoded in the ID).

## Allowed Formats
- UUID (recommended)
- String-based opaque tokens
- Numeric identifiers only if legacy systems require them

## Field Naming
- Must end with `Id`.
- Examples:
  - `customerId`
  - `accountId`
  - `transactionId`

## Cross-Resource Consistency
- A `customerId` must represent the same type of entity everywhere.
- Do not mix terminology (e.g., `clientId` vs `customerId`).

## No Sensitive Data
- Identifiers must never contain personal data.
- Must not contain:
  - IBANs  
  - Emails  
  - Phone numbers  
  - Social security numbers

