# Versioning Rules

## General
- APIs must be versioned.
- Major version only (`v1`, `v2`).
- No minor or patch versions in the path.

## Version Placement
Choose one strategy — mixing is not allowed.

### Option A: Version in Path (preferred)
```
/v1/accounts
/v1/transactions
```

### Option B: Version via Header
```
X-API-Version: 1
```

## Breaking Changes
Major version must be incremented when:
- Removing fields
- Renaming fields
- Changing field types
- Changing authentication
- Changing response shapes
- Changing HTTP semantics

## Non-breaking Changes
Do NOT require new version if:
- Adding optional fields
- Adding new endpoints
- Adding new error codes

