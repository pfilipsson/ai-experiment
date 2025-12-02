(PATH, STRUCTURE) Paths MUST start with a leading forward slash (/).
(PATH, STRUCTURE) Paths MUST NOT end with a trailing slash (/).
(PATH, STRUCTURE) Paths SHOULD follow a hierarchical structure representing resources.
(PATH, STRUCTURE) Paths SHOULD avoid unnecessary nesting beyond three levels.
(PATH, STRUCTURE) Paths MUST NOT include file extensions (e.g., .json, .xml).

(PATH, NAMING) Path segments MUST use lowercase letters.
(PATH, NAMING) Path segments MUST use hyphens to separate words.
(PATH, NAMING) Path segments MUST NOT use underscores.
(PATH, NAMING) Path segments MUST NOT use uppercase letters.
(PATH, NAMING) Path segments MUST NOT use camelCase or PascalCase.
(PATH, NAMING) Path segments SHOULD use descriptive nouns.
(PATH, NAMING) Path segments MUST use plural nouns when representing collections.

(PATH, PARAMETERS) Path parameters MUST be surrounded by curly braces (e.g., {accountId}).
(PATH, PARAMETERS) Path parameters MUST use camelCase.
(PATH, PARAMETERS) Path parameters MUST NOT use snake_case or kebab-case.
(PATH, PARAMETERS) Path parameters MUST be descriptive and reflect the resource identifier.
(PATH, PARAMETERS) Path parameters SHOULD avoid single-character names (e.g., {x}).
(PATH, PARAMETERS) Path parameters representing UUIDs SHOULD be named id or {resourceId} (e.g., accountId).

(PATH, CONSISTENCY) Resources MUST use consistent naming across the entire API.
(PATH, CONSISTENCY) The same resource MUST NOT appear with different names in different endpoints.
(PATH, CONSISTENCY) Nested resources MUST follow standard parent-child patterns (e.g., /accounts/{accountId}/transactions).
(PATH, CONSISTENCY) The structure of collection and item paths MUST be consistent across all resources.

(PATH, ACTIONS) Paths MUST represent resources, not actions.
(PATH, ACTIONS) RPC-style verbs MUST NOT appear in paths (e.g., /getBalance, /createUser).
(PATH, ACTIONS) Non-resource actions SHOULD be expressed u
