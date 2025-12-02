REST paths must begin with a lowercase noun.

Use plural nouns for collections (e.g., /accounts).

Use singular nouns with path parameters for specific resources.

Paths must not contain verbs.

GET operations must fetch data with no side effects.

POST operations must create new resources.

PUT operations must fully replace resources.

PATCH operations must partially update resources.

DELETE operations must remove resources.

Path parameters must be defined explicitly in parameters.

Path parameter names must match exactly and be case-sensitive.

Path parameter names must use camelCase.

Filtering must use standard query parameters (limit, offset, page, pageSize, fromDate, toDate).

Filtering must not use non-standard parameters like maxItems, skip, or take.

GET collection responses must return an array and pagination metadata when relevant.

Pagination metadata should include items, total, page, and pageSize.

When using versioning in paths, only major versions with a leading v are allowed (e.g., /v1/...).

No trailing slashes are allowed in paths.

Paths must not include file extensions (e.g., .json, .yaml).