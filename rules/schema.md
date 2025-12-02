(SCHEMA, NAMING) Schema object names SHOULD use PascalCase.
(SCHEMA, NAMING) Schema object names MUST be descriptive and represent a clear business concept.
(SCHEMA, NAMING) Schema object names MUST NOT use camelCase, snake_case, or kebab-case.
(SCHEMA, NAMING) Schema object names MUST NOT be plural nouns.

(SCHEMA, STRUCTURE) JSON objects SHOULD represent domain concepts rather than technical artifacts.
(SCHEMA, STRUCTURE) JSON objects MUST group related fields and avoid overloading multiple concepts in a single object.
(SCHEMA, STRUCTURE) Resource identifiers MUST use consistent naming (e.g., accountId, customerId).
(SCHEMA, STRUCTURE) Embedded objects SHOULD be used when they add clarity to the response.
(SCHEMA, STRUCTURE) Arrays MUST represent lists of homogeneous items.

(SCHEMA, REQUIRED) Required fields MUST be explicitly specified using the OpenAPI `required` list.
(SCHEMA, REQUIRED) Fields essential for resource identity or behavior SHOULD be marked as required.
(SCHEMA, REQUIRED) Non-essential fields SHOULD NOT be marked as required.

(SCHEMA, TYPES) Field types MUST match their actual semantics (e.g., IDs as strings, monetary values as decimals).
(SCHEMA, TYPES) Boolean fields MUST NOT be represented as strings.
(SCHEMA, TYPES) Date fields MUST use ISO 8601 format (e.g., 2024-01-15T12:34:56Z).
(SCHEMA, TYPES) Numeric fields representing currency SHOULD use decimal types, not floating point.
(SCHEMA, TYPES) UUIDs MUST be represented as strings.
(SCHEMA, TYPES) Empty objects MUST NOT be used as placeholders.

(SCHEMA, ENUM) Enumerated fields MUST define allowed values explicitly through enums.
(SCHEMA, ENUM) Enum values MUST use uppercase snake_case (e.g., PENDING_APPROVAL).
(SCHEMA, ENUM) Enum values MUST NOT mix cases or formats.
(SCHEMA, ENUM) Adding new enum values MUST be considered a breaking change unless clients can safely ignore unknown values.

(SCHEMA, EXAMPLES) All request and response objects SHOULD include example values.
(SCHEMA, EXAMPLES) Examples SHOULD use realistic data that illustrates real-world usage.
(SCHEMA, EXAMPLES) Examples MUST NOT include sensitive or production data.
(SCHEMA, EXAMPLES) Error objects SHOULD include example error payloads.

(SCHEMA, CONSISTENCY) Field names MUST use camelCase across all schemas.
(SCHEMA, CONSISTENCY) The same business concept MUST use the same field name everywhere in the API.
(SCHEMA, CONSISTENCY) Identical fields MUST use identical types across resources.
(SCHEMA, CONSISTENCY) Optional fields SHOULD follow the same naming and structure across all versions.

(SCHEMA, VERSIONING) Schema changes that remove fields MUST be treated as breaking changes.
(SCHEMA, VERSIONING) New optional fields SHOULD be considered additive and non-breaking.
