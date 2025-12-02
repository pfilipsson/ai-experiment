Business identifiers must be stable and non-changing.

Business identifiers must be unique within their domain.

Business identifiers should be opaque and must not encode meaning.

Allowed identifier formats include UUID, opaque strings, or numeric IDs only for legacy reasons.

Fields representing identifiers must end with Id.

Identifier fields must use consistent naming, such as customerId, accountId, and transactionId.

The same identifier name must refer to the same type of entity across all APIs.

Do not mix terminology (e.g., do not use both clientId and customerId).

Identifiers must not contain sensitive data.

Identifiers must not contain IBANs, emails, phone numbers, or social security numbers.