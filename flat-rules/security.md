All endpoints must require HTTPS.

Plain HTTP must be rejected using 301 or explicit error.

Allowed authentication mechanisms include OAuth2, JWT bearer tokens, and API keys for limited scenarios.

JWT tokens must have exp validated.

JWT tokens must have iss validated.

JWT tokens must have aud validated.

APIs must never return passwords, full credit card numbers, CVVs, or secret API keys.

IBAN values must be masked unless strictly necessary.

Scopes must be domain-specific (e.g., accounts.read).

Scopes must not be overly generic (e.g., read, all).

Each operation must declare a security mechanism in the security section.

CORS rules must explicitly define allowed origins.

CORS must not allow * unless the API is public.