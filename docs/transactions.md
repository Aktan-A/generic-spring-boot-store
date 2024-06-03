# Transaction Endpoints

### GET `/api/v1/transactions/{transaction_id}`

- Description: Get transaction details by id
- Headers:
```
  Authorization: Bearer token
```

- Response 200

```json
{
    "id": 1,
    "totalPrice": 100.0,
    "orderId": 1,
    "status": "CREATED",
    "createdAt": "2024-05-23T17:55:22.87249"
}
```

### GET `/api/v1/transactions`

- Description: Get all transactions
- Headers:
```
  Authorization: Bearer token
```

- Response 200

```json
[
    {
    "id": 1,
    "totalPrice": 100.0,
    "orderId": 1,
    "status": "CREATED",
    "createdAt": "2024-05-23T17:55:22.87249"
    }
]
```

### POST `/api/v1/transactions`

- Description: Create a transaction
- Headers:
```
  Authorization: Bearer token
```

- Request Body:
```json
{
    "totalPrice": 100,
    "orderId": 1
}
```

- Response 201

```json
{
    "id": 1,
    "totalPrice": 100.0,
    "orderId": 1,
    "status": "CREATED",
    "createdAt": "2024-05-23T17:55:22.87249"
}
```

### PUT `/api/v1/transactions/{transaction_id}`

- Description: Update a transaction by id
- Headers:
```
  Authorization: Bearer token
```

- Request Body:
```json
{
    "totalPrice": 100.0,
    "status": "SUCCESS"
}
```

- Response 200

```json
{
    "id": 1,
    "totalPrice": 100.0,
    "orderId": 1,
    "status": "SUCCESS",
    "createdAt": "2024-05-23T17:55:22.87249"
}
```

### DELETE `/api/v1/transactions/{transaction_id}`

- Description: Delete a transaction by id
- Headers:
```
  Authorization: Bearer token
```

- Response 200

```json
Transaction successfully deleted.
```