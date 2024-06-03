# Auth Endpoints

### POST `/api/v1/auth/register`

- Description: Register a user

- Request Body:
```json
{
    "username": "johndoe2",
    "password": "123",
    "firstName": "John",
    "lastName": "Doe",
    "address": "This Avenue 73"
}
```

- Response 200

```json
{
    "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huZG9lMiIsImlhdCI6MTcxNzM4NTA0NywiZXhwIjoxNzE3Mzg2NDg3fQ.SFF2fZA4BPSINo8koql9WnXazlDJYZkXLfMQKvA0rHE"
}
```

### POST `/api/v1/auth/login`

- Description: Login

- Request Body:
```json
{
    "username": "johndoe2",
    "password": "123"
}
```

- Response 200

```json
{
    "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huZG9lMiIsImlhdCI6MTcxNzM4NTA0NywiZXhwIjoxNzE3Mzg2NDg3fQ.SFF2fZA4BPSINo8koql9WnXazlDJYZkXLfMQKvA0rHE"
}
```