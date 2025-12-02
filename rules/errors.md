(ERROR, SCHEMA) Error responses MUST return a structured error object rather than plain text.
(ERROR, SCHEMA) Error responses MUST include a machine-readable error code.
(ERROR, SCHEMA) Error responses MUST include a human-readable error message.
(ERROR, SCHEMA) Error responses MUST include an HTTP status code that matches the error condition.
(ERROR, SCHEMA) Error response objects SHOULD include a unique trace or correlation identifier.
(ERROR, SCHEMA) Error response objects SHOULD include a field describing the type or category of error.

(ERROR, SCHEMA) Validation errors MUST include details describing which input fields failed validation.
(ERROR, SCHEMA) Validation errors MUST include field-specific error information when applicable.
(ERROR, SCHEMA) Validation errors SHOULD group multiple validation issues in a list or array.

(ERROR, NAMING, SCHEMA) Error response properties MUST follow camelCase naming conventions.
(ERROR, NAMING, SCHEMA) Error schema names SHOULD follow PascalCase (e.g., ErrorResponse).

(ERROR) 400 MUST be used for invalid input or malformed requests.
(ERROR) 401 MUST be used when authentication is required and missing or invalid.
(ERROR) 403 MUST be used when the user is authenticated but not authorized.
(ERROR) 404 MUST be used when a requested resource cannot be found.
(ERROR) 409 SHOULD be used when a request conflicts with the current resource state.
(ERROR) 500 MUST NOT be used for client-side input errors.
(ERROR) 500 SHOULD only be used for unexpected server faults or unavailable dependencies.

(ERROR, CONSISTENCY) All error responses across an API MUST follow the same structure.
(ERROR, CONSISTENCY) Error codes SHOULD be predictable and consistent across endpoints.
(ERROR, CONSISTENCY) Error categories SHOULD be reused across services where applicable.
(ERROR, CONSISTENCY) Error messages SHOULD avoid exposing internal implementation details.
