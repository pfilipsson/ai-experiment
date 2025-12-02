# AI API Analysis Response Evolution

This file contains the responses as we make changes to the system. The swagger file used is the one in the samples folder called `example.yml`.

# Iteration 1

Rules were written in human readable markdown format with headings etc. When searching for rules always sending same search.

```
Here is the compliance report for “Sample API” against the provided guidelines.

Critical issues

- Versioning not compliant with “major-only” rule

  - Evidence: info.version = "1.0"

  - Guideline: Versioning Rules → General → “APIs must be versioned. Major version only (v1, v2).”

  - Why: "1.0" includes a minor segment and lacks the leading “v”.

  - Remediation: Change version to "v1". If you also choose to version in the path, use "/v1/..." and keep only the major version.



Warnings

- No versioning in the path (optional, but common)

  - Evidence: path is "/hello" (no "/v1/...").

  - Guideline: Versioning in Path (if used) → “Must use leading v” and “Only major version.”

  - Note: This is a warning since path versioning is optional. If adopted, ensure "/v1/hello".

- No error format or validation error contract defined

  - Evidence: No error schemas/responses provided.

  - Guideline: Error Handling Rules → Validation Errors (content is incomplete but implies a required, consistent format).

  - Remediation: Define a standard error schema and document validation error structure.

- No security scheme declared

  - Evidence: Security: []

  - Guideline: Security Rules (section present but content is unspecified).

  - Note: Because the security rules are unspecified, treat as a warning. If the API is public/unauthenticated, state this explicitly and document any rate limiting; otherwise define an auth scheme (e.g., OAuth2, API key, or JWT) and 401/403 responses.



Suggestions

- Make the endpoint name more descriptive

  - Evidence: Path "/hello".

  - Guideline: Naming Rules → “Names must be descriptive and unambiguous.”

  - Remediation: Consider something like "/greetings" or a business-context path (e.g., "/status", "/health") depending on intent.

- Provide at least one schema example to demonstrate naming compliance

  - Evidence: Schemas: [].

  - Guideline: Naming Rules → Properties camelCase; schema names PascalCase; avoid ALL_CAPS/snake/kebab; avoid non-standard abbreviations.

  - Remediation: Add a sample response/request model (e.g., GreetingResponse) to show adherence.

- Clarify and de-duplicate the guideline document

  - Evidence: Repetitive section headers (“# Naming Rules” inside Versioning Rules; repeated “# Security Rules”), incomplete Validation Errors list.

  - Impact: Ambiguity makes enforcement difficult.

  - Remediation: Clean up headings, explicitly list required error fields (e.g., code, message, details, fieldErrors), and state whether versioning must appear in the path, info.version, or both.



Positive observations

- No disallowed naming styles observed in fields (no snake_case, kebab-case, or ALL_CAPS in the provided artifacts).

- No minor/patch version appears in the path (consistent with the path-specific rule).

- Minimal, readable path and method; easy to extend once versioning and error contracts are added.


Quick fix summary

- Set API version to "v1" (info.version).

- Optionally version the path: "/v1/hello".

- Define and document a standard error response schema and validation error format.

- If authentication is required, add a security scheme and 401/403 responses; if not, document that the API is public.

- Rename "/hello" to a more descriptive resource name aligned with the API’s purpose.
```

# Iteration 2

Rules were written in markdown format using flat lists. When searching for rules always sending same search.

```
Structured Compliance Report for "Sample API" v1.0



Critical issues

- Versioning not applied to endpoints: Rule requires all APIs to be versioned; current path (/hello) lacks a version segment and no alternate strategy (e.g., header or media type) is documented. If path versioning is used, it must be /v{major} only (e.g., /v1/...). This is a blocking issue.

- Error handling model undefined: No RFC 9457-compliant error responses are specified. Violations include:

  - No standard problem details structure with type, title, status (and detail/instance as applicable).

  - No defined 400 responses with an errors array for field-level validation issues.

  - No defined 422 responses for domain validation failures.

  - No declared application/problem+json media type or reusable error schemas.



Warnings

- Endpoint naming lacks descriptiveness: /hello is vague and action-like, not a clear resource. Violates “Names must be descriptive and unambiguous.”

- API title is generic: “Sample API” is not descriptive of domain or purpose; may create ambiguity in documentation and discovery.

- Absence of schemas: While not a direct violation, the lack of component schemas prevents enforcing naming rules (PascalCase, singular object names) and consistent reuse across responses.



Suggestions

- Choose and document a single versioning strategy:

  - Recommended: path versioning with major-only, e.g., /v1/greetings or /v1/greeting.

  - If you prefer header or media type versioning, document the header/content negotiation and ensure paths remain unversioned; do not mix strategies.

- Define RFC 9457 Problem Details payloads and wire them into responses:

  - For validation errors (400): include type, title, status, and an errors array with field-level issues (e.g., field, message, code).

  - For domain validation failures (422): include type, title, status; add domain-specific details.

  - Use application/problem+json and include reusable schemas (e.g., ProblemDetails, ValidationProblemDetails) in components.

- Improve resource naming and RESTfulness:

  - Replace /hello with a descriptive, noun-based resource. Examples:

    - /v1/greetings (collection; GET returns a list)

    - /v1/greeting (singular; GET returns a single greeting)

  - If returning arrays, use plural resource names (e.g., greetings).

- Adopt and document naming conventions in schemas:

  - Schema/object names in PascalCase (e.g., Greeting, GreetingSummary).

  - Object names singular; array properties plural.

  - Identifier fields end with Id (e.g., greetingId, userId).

- Provide examples and contracts:

  - Add example 400 and 422 responses showing required fields and errors array content.

  - Define response/request schemas for the GET /greeting(s) endpoint to solidify model naming compliance.

- (If applicable) Define security:

  - If any authentication is required, declare security schemes and apply them consistently.



Positive observations

- Version metadata (1.0) is present, showing awareness of versioning and major/minor semantics.

- Minimal surface area simplifies adoption of consistent naming/versioning/error patterns before adding more endpoints.


- GET is used for a read-only operation, aligning with HTTP method semantics.
```

# Iteration 3

Rules were written in markdown format using flat lists and with categories/tags. Rules are specific and touch only on one concept. We introduce some level of dynamic search for rules.

```
Here is a compliance report for “Sample API” v1.0 based on the provided guidelines.

Critical issues
- None detected. No MUST-level violations are evident from the available metadata.

Warnings
- Path naming semantics: /hello is not a descriptive domain noun (PATH, NAMING: path segments SHOULD use descriptive nouns).
- Missing response modeling and examples: No response/request schemas or examples are provided, making it impossible to meet example guidance and to validate schema-related MUSTs when you add fields later (SCHEMA, EXAMPLES: SHOULD include examples; SCHEMA, STRUCTURE/TYPES: future compliance untestable).
- Error handling not documented: No standardized error responses or example error payloads are defined (ERROR, SCHEMA and SCHEMA, EXAMPLES: SHOULD include error payload examples; status-code usage guidance is not reflected).
- Naming convention coverage is untestable: With no schemas, parameters, or multiple resources, consistency and casing rules cannot be verified across the API (NAMING, CONSISTENCY).

Suggestions
- Make the resource path descriptive:
  - If this is a greeting resource, consider /greetings (collection) or /greetings/{greetingId} (item).
  - If this is a health endpoint, consider /health or /status (nouns).
- Document versioning strategy:
  - If adopting URL-based versioning, use /v1 as the first path segment (VERSIONING, PATH) and avoid decimals in the URL.
  - Otherwise document header-based or media-type versioning explicitly.
- Define response/request schemas in components:
  - Use PascalCase names that reflect business concepts (e.g., Greeting, ErrorResponse).
  - Ensure fields follow type rules (IDs as strings/UUIDs, dates in ISO 8601, booleans as booleans, currency as decimals).
  - Provide realistic, non-sensitive example payloads for all objects, including errors.
- Standardize error responses:
  - For applicable cases include 400, 401, 403, 404, and 409 with clear semantics.
  - Use a consistent error schema with camelCase properties (e.g., code, message, fieldErrors[]), and provide example error payloads.
- Prepare for parameter naming:
  - When adding query/path parameters, use camelCase and descriptive names (e.g., greetingId, pageSize).
- Collections and top-level fields:
  - For list endpoints, return arrays under plural top-level names (e.g., "greetings") and ensure top-level fields are descriptive domain terms (NAMING, RESPONSE).

Positive observations
- Path conforms to formatting basics:
  - Starts with a leading slash, no trailing slash, no file extensions (PATH, STRUCTURE).
  - Lowercase, no underscores, no camelCase/PascalCase in the segment (PATH, NAMING).
- Simple, shallow path structure; no unnecessary nesting (PATH, STRUCTURE).
- No misuse of URL-based versioning (no decimals or misplaced version segment).
```


