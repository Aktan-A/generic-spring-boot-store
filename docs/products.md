# Product Endpoints

### GET `/api/v1/products/{product_id}`

- Description: Get product details by id
- Headers:
```
  Authorization: Bearer token
```

- Response 200

```json
{
    "id": 1,
    "name": "Product name",
    "status": "ACTIVE",
    "description": "Product description",
    "price": 4.99,
    "createdAt": "2024-05-16T16:44:42.459726"
}
```

### GET `/api/v1/products`

- Description: Get all products
- Headers:
```
  Authorization: Bearer token
```

- Response 200

```json
[
    {
    "id": 1,
    "name": "Product name",
    "status": "ACTIVE",
    "description": "Product description",
    "price": 4.99,
    "createdAt": "2024-05-16T16:44:42.459726"
    }
]
```

### POST `/api/v1/products`

- Description: Create a product
- Headers:
```
  Authorization: Bearer token
```

- Request Body:
```json
{
    "name": "Coffee beans",
    "status": "ACTIVE",
    "description": "1kg bag of Columbia coffee beans",
    "price": 14.99
}
```

- Response 201

```json
{
    "id": 1,
    "name": "Product name",
    "status": "ACTIVE",
    "description": "Product description",
    "price": 4.99,
    "createdAt": "2024-05-16T16:44:42.459726"
}
```

### PUT `/api/v1/products/{product_id}`

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
    "name": "Product name",
    "status": "ACTIVE",
    "description": "Product description",
    "price": 4.99,
    "createdAt": "2024-05-16T16:44:42.459726"
}
```

### DELETE `/api/v1/products/{product_id}`

- Description: Delete a product by id
- Headers:
```
  Authorization: Bearer token
```

- Response 200

```json
Product successfully deleted.
```