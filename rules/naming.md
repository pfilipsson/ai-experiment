(NAMING, JSON) JSON property names MUST use camelCase.
(NAMING, JSON) JSON property names SHOULD avoid abbreviations unless they are industry-standard.
(NAMING, JSON) JSON property names SHOULD avoid acronyms unless they improve clarity.
(NAMING, JSON) JSON property names SHOULD be descriptive and unambiguous.
(NAMING, JSON) JSON property names MUST NOT use snake_case.
(NAMING, JSON) JSON property names MUST NOT use kebab-case.
(NAMING, JSON) JSON property names MUST NOT use PascalCase.

(NAMING, SCHEMA) Schema object names SHOULD use PascalCase.
(NAMING, SCHEMA) Schema object names MUST be descriptive and reflect the business concept they model.
(NAMING, SCHEMA) Schema object names MUST NOT use camelCase.
(NAMING, SCHEMA) Schema object names MUST NOT use plural nouns.

(NAMING, PATH) Path segments MUST use lowercase letters.
(NAMING, PATH) Path segments MUST use hyphens to separate words.
(NAMING, PATH) Path segments MUST NOT use underscores.
(NAMING, PATH) Path segments MUST NOT use uppercase letters.
(NAMING, PATH) Path segments MUST NOT use camelCase or PascalCase.
(NAMING, PATH) Path segments SHOULD use descriptive nouns instead of generic terms.
(NAMING, PATH) Path segments MUST use plural nouns when representing collections.

(NAMING, PARAMETERS) Query parameter names MUST use camelCase.
(NAMING, PARAMETERS) Query parameter names SHOULD be descriptive and consistent across endpoints.
(NAMING, PARAMETERS) Path parameter names MUST use camelCase.
(NAMING, PARAMETERS) Path parameter names SHOULD reflect the unique identifier of the resource (e.g., accountId).

(NAMING, CONSISTENCY) Naming conventions MUST be applied consistently across the entire API.
(NAMING, CONSISTENCY) The same concept MUST use the same name everywhere in the API.
(NAMING, CONSISTENCY) Abbreviations SHOULD be avoided unless consistently used across all resources.

(NAMING, RESPONSE) Top-level response fields SHOULD use descriptive names that match their domain concept.
(NAMING, RESPONSE) List endpoints SHOULD use plural names (e.g., "accounts") for top-level arrays.
