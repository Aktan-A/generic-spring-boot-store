# User Endpoints

### GET `/api/v1/users/{user_id}`

- Description: Get user details by id
- Headers:
```
  Authorization: Bearer token
```

- Response 200

```json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "address": "This Avenue 73",
  "createdAt": "2024-05-23T16:00:03.220392"
}
```

### GET `/api/v1/users`

- Description: Get all users
- Headers:
```
  Authorization: Bearer token
```

- Response 200

```json
[
    {
        "id": 1,
        "firstName": "John",
        "lastName": "Doe",
        "address": "This Avenue 73",
        "createdAt": "2024-05-23T16:00:03.220392"
    }
]
```

### PUT `/api/v1/users/{user_id}`

- Description: Update a user by id
- Headers:
```
  Authorization: Bearer token
```

- Request Body:
```json
{
    "firstName": "John",
    "lastName": "Doe",
    "address": "This Avenue 73"
}
```

- Response 200

```json
{
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "address": "This Avenue 73",
    "createdAt": "2024-05-23T16:00:03.220392"
}
```

### DELETE `/api/v1/users/{user_id}`

- Description: Delete a user by id
- Headers:
```
  Authorization: Bearer token
```

- Response 200

```json
User successfully deleted.
```