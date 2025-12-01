# Path & Operation Rules

## General REST Path Structure
- Paths must start with a noun, all lowercase.
- Use plural nouns for collections (`/accounts`).
- Use singular with path parameters for specific resources (`/accounts/{accountId}`).
- No verbs in paths.

## HTTP Verb Semantics
- GET: fetch data, no side effects.
- POST: create a new resource.
- PUT: full update.
- PATCH: partial update.
- DELETE: remove resource.

## Path Parameters
- Must be defined in the `parameters` section.
- Must match exactly (case-sensitive).
- Must use camelCase (e.g., `transactionId`, not `transaction_id`).

## Filtering and Pagination
- Use standard query parameters:
  - `limit`
  - `offset`
  - `page`
  - `pageSize`
  - `fromDate`, `toDate`
- No custom names like `maxItems`, `skip`, `take`.

## Response Consistency
- GET collection must return:
  - an array  
  - pagination metadata if applicable  

Example:

```json
{
  "items": [...],
  "total": 123,
  "page": 1,
  "pageSize": 25
}
```

## Versioning in Path (if used)
- Must use leading `v`:
  - `/v1/accounts`
- Only major version is allowed in path.

## No Trailing Slashes
- `/accounts` is correct.
- `/accounts/` is incorrect.

## No File Extensions
- Do not use `.json`, `.yaml`, etc. in URLs.
