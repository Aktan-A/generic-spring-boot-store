# Customer Endpoints

### GET `/api/v1/customers/{customer_id}`

- Description: Get customer details by id

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

### GET `/api/v1/customers`

- Description: Get all customers

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

### POST `/api/v1/customers`

- Description: Create a customer

- Request Body:
```json
{
    "firstName": "John",
    "lastName": "Doe",
    "address": "This Avenue 73"
}
```

- Response 201

```json
{
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "address": "This Avenue 73",
    "createdAt": "2024-05-23T16:00:03.220392"
}
```

### PUT `/api/v1/customers/{customer_id}`

- Description: Update a customer by id

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

### DELETE `/api/v1/customers/{customer_id}`

- Description: Delete a customer by id

- Response 200

```json
Customer successfully deleted.
```