# Naming Rules

## General
- Names must use `camelCase` for properties.
- Use `PascalCase` for schema (object) names.
- Do not use ALL_CAPS, snake_case, or kebab-case in JSON fields.
- Names must be descriptive and unambiguous.
- Avoid abbreviations unless they are industry-standard (e.g., `iban`, `bic`, `mcc`).

## Fields ending with "Id"
- Must represent a unique identifier.
- Must use the suffix `Id` exactly (not ID, id, or Ids).
- Should be a string unless explicitly numeric.

## Pluralization
- Arrays must be plural.
- Objects must be singular.
- Example:
  - Correct: `accounts`, `
