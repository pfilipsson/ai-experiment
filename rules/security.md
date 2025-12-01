# Security Rules

## Transport
- All endpoints must require HTTPS.
- Reject plain HTTP via 301 or explicit error.

## Authentication
Supported mechanisms:
- OAuth2 (Client Credentials or Authorization Code)
- JWT bearer tokens
- API keys (only for internal/non-sensitive APIs)

## Token Requirements
If JWT is used:
- Must validate expiration (`exp`)
- Must validate issuer (`iss`)
- Must validate audience (`aud`)

## Sensitive Data
- Never return:
  - passwords
  - full credit card numbers
  - CVV
  - secret API keys
- IBAN must be masked unless strictly necessary.

## Scopes
- Use domain-specific scopes (e.g., `accounts.read`).
- Do not use generic scopes like `read` or `all`.

## Security in OpenAPI
Each operation must declare one of:

```yaml
security:
  - bearerAuth: []
```

or

```yaml
security:
  - apiKeyAuth: []
```

## CORS
- Must explicitly allow origins.
- Avoid `*` unless public API.

