# Error Format Rules

## Standard Error Structure
All error responses must follow this structure:

```json
{
  "type": "string - stable identifier",
  "title": "short human readable title",
  "status": 400,
  "detail": "more detailed human description",
  "instance": "/path/to/request"
}
```

This follows RFC 9457 (successor to RFC 7807).

## Required Fields
- `type`
- `title`
- `status`

## Status Codes
- 400 Bad Request — validation errors
- 401 Unauthorized — missing/invalid authentication
- 403 Forbidden — authenticated but not allowed
- 404 Not Found — resource does not exist
- 409 Conflict — duplicates, business conflicts
- 422 Unprocessable Entity — domain validation

## Validation Errors
If validation fails, include:

```json
"errors": [
  {
    "field": "customerId",
    "message": "customerId must be a valid UUID"
  }
]
```

## Do Not
- Do not use `errorCode` without documentation.
- Do not return raw stack traces.
- Do not overload 500 for known client errors.

