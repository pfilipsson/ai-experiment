All error responses must follow RFC 9457 structure.

Error responses must include type, title, and status fields.

Use 400 for validation errors.

Use 401 for missing or invalid authentication.

Use 403 for authenticated users lacking permission.

Use 404 when the resource does not exist.

Use 409 for business conflicts or duplicates.

Use 422 for domain validation failures.

Validation errors must include an errors array listing field-level issues.

Fields in validation errors must contain field and message.

Do not use undocumented errorCode values.

Do not return raw stack traces.

Do not use 500 for known client errors.