(SECURITY, AUTHENTICATION) Endpoints that access user-specific or sensitive data MUST require authentication.
(SECURITY, AUTHENTICATION) Authentication MUST be based on a secure token mechanism such as OAuth2 or OIDC.
(SECURITY, AUTHENTICATION) Endpoints MUST NOT rely on API keys passed in query parameters.
(SECURITY, AUTHENTICATION) Endpoints SHOULD NOT allow authentication using basic authentication with username and password.
(SECURITY, AUTHENTICATION) All authentication mechanisms MUST use HTTPS.

(SECURITY, AUTHORIZATION) Endpoints MUST validate that the authenticated user is authorized to access the requested resource.
(SECURITY, AUTHORIZATION) Authorization checks MUST NOT rely solely on client-provided identifiers.
(SECURITY, AUTHORIZATION) Role- or scope-based access SHOULD be used to control permissions.
(SECURITY, AUTHORIZATION) Access tokens SHOULD include scope or permission claims that the API enforces.

(SECURITY, DATA) Sensitive fields such as passwords, tokens, or secrets MUST NOT appear in responses.
(SECURITY, DATA) Sensitive fields MUST NOT be accepted as plain text unless encrypted in transit.
(SECURITY, DATA) Personally identifiable information SHOULD be minimized in responses.
(SECURITY, DATA) Error messages MUST NOT expose sensitive information, stack traces, or internal implementation details.

(SECURITY, TRANSPORT) All endpoints MUST enforce TLS (HTTPS) for all requests.
(SECURITY, TRANSPORT) APIs MUST NOT allow insecure protocols such as HTTP.
(SECURITY, TRANSPORT) APIs SHOULD use modern TLS configurations and avoid deprecated ciphers.

(SECURITY, INPUT_VALIDATION) All inputs MUST be validated to prevent injection or malformed data.
(SECURITY, INPUT_VALIDATION) Path and query parameters MUST be validated before use.
(SECURITY, INPUT_VALIDATION) Request bodies MUST be validated against their schema definitions.
(SECURITY, INPUT_VALIDATION) Validation SHOULD enforce correct data types, ranges, and formats.

(SECURITY, RATE_LIMITING) APIs SHOULD apply rate limiting to protect against brute-force or abuse scenarios.
(SECURITY, RATE_LIMITING) Rate-limited responses SHOULD return HTTP 429 with a Retry-After header.

(SECURITY, RESPONSE_HEADERS) APIs SHOULD include security-related HTTP headers such as Cache-Control and X-Content-Type-Options.
(SECURITY, RESPONSE_HEADERS) APIs SHOULD discourage caching of sensitive data using appropriate headers.

(SECURITY, TOKEN) Access tokens MUST be transmitted via the Authorization header using the Bearer scheme.
(SECURITY, TOKEN) Access tokens MUST NOT be included in URLs or query parameters.
(SECURITY, TOKEN) Refresh tokens MUST NEVER be transmitted to or exposed within frontend clients.
(SECURITY, TOKEN) JWT tokens SHOULD have reasonable expiration times and include issued-at (iat) and expiration (exp) claims.

(SECURITY, CONSISTENCY) Security requirements MUST be applied consistently across all endpoints.
(SECURITY, CONSISTENCY) Endpoints within the same domain or resource group MUST use the same authentication and authorization model.
(SECURITY, CONSISTENCY) Public endpoints MUST be explicitly documented as public.
