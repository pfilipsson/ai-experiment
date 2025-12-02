All APIs must be versioned.

API versions must use major version only (e.g., v1, v2).

Minor or patch versions must not appear in the path.

Only one versioning strategy may be used.

If using path versioning, the version must appear as /v{major}.

If using header versioning, the header must be X-API-Version: {major}.

Breaking changes require incrementing the major version.

Breaking changes include removing fields, renaming fields, changing field types, modifying auth requirements, changing response structures, or altering HTTP semantics.

Non-breaking changes must not cause a new major version.

Adding optional fields, endpoints, or error codes does not require a new version.