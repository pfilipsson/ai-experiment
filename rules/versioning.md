(VERSIONING, GENERAL) APIs MUST use explicit versioning.
(VERSIONING, GENERAL) APIs MUST use major versioning to signal breaking changes.
(VERSIONING, GENERAL) Minor or patch versions MUST NOT be exposed in URL paths.
(VERSIONING, GENERAL) The versioning strategy MUST be applied consistently across all endpoints.

(VERSIONING, PATH) When using URL-based versioning, the version MUST appear as the first path segment (e.g., /v1/).
(VERSIONING, PATH) URL-based versions MUST use the format v{major}, such as v1 or v2.
(VERSIONING, PATH) URL-based versions MUST NOT include decimals (e.g., /v1.2/ is not allowed).
(VERSIONING, PATH) URL-based versions MUST NOT include patch versions (e.g., /v1.0.3/ is not allowed).

(VERSIONING, HEADER) When using header-based versioning, the version MUST be conveyed through a custom or standard version header.
(VERSIONING, HEADER) Header-based versioning SHOULD use a semantically meaningful header such as API-Version or Accept-Version.
(VERSIONING, HEADER) Header-based versions SHOULD use integers representing the major version.
(VERSIONING, HEADER) Clients SHOULD be able to specify the version via HTTP headers without modifying the URL.

(VERSIONING, MEDIA_TYPE) Media type versioning MUST include only the major version (e.g., application/vnd.company.v1+json).
(VERSIONING, MEDIA_TYPE) Media type versioning MUST NOT encode minor or patch versions.
(VERSIONING, MEDIA_TYPE) Media type versioning MUST follow the pattern application/vnd.{org}.v{major}+json.

(VERSIONING, BREAKING_CHANGES) Any breaking change MUST trigger a major version increment.
(VERSIONING, BREAKING_CHANGES) Breaking changes MUST NOT be introduced in patch or minor updates.
(VERSIONING, BREAKING_CHANGES) Removing fields, renaming fields, or modifying behavior in incompatible ways MUST be treated as breaking changes.

(VERSIONING, NON_BREAKING) Additive, backward-compatible changes SHOULD be introduced without a major version bump.
(VERSIONING, NON_BREAKING) Adding optional fields SHOULD be treated as a non-breaking change.
(VERSIONING, NON_BREAKING) Adding new endpoints SHOULD NOT require a new version unless they change existing behavior.

(VERSIONING, DEFAULT) APIs SHOULD define a default version for clients who do not specify one.
(VERSIONING, DEFAULT) Default versions SHOULD be stable and SHOULD NOT change without a migration plan.

(VERSIONING, DEPRECATION) Deprecated versions MUST be documented and communicated to consumers.
(VERSIONING, DEPRECATION) Deprecation notices SHOULD include timelines, migration steps, and alternative versions.
(VERSIONING, DEPRECATION) Deprecated versions SHOULD remain available for a reasonable grace period before removal.
(VERSIONING, DEPRECATION) Removal of a deprecated version MUST follow prior communication and version lifecycle policy.

(VERSIONING, CONSISTENCY) All endpoints within a version MUST adhere to the same design rules.
(VERSIONING, CONSISTENCY) A given version MUST expose consistent schemas across all its resources.
(VERSIONING, CONSISTENCY) Mixed-version endpoints MUST NOT exist within the same resource group.

(VERSIONING, DOCUMENTATION) Each version MUST have its own documentation reflecting the API behavior for that version.
(VERSIONING, DOCUMENTATION) Documentation MUST specify which changes are breaking and which are additive.
(VERSIONING, DOCUMENTATION) Version lifecycle status (active, deprecated, sunset) SHOULD be documented.
