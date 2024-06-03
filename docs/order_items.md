# Order Item Endpoints

### POST `/api/v1/orders_items`

- Description: Create an order item

- Headers:
```
  Authorization: Bearer token
```

- Request Body:
```json
{
    "quantity": 1,
    "orderId": 1,
    "productId": 1
}
```

- Response 201

```json
{
    "id": 1,
    "quantity": 1,
    "orderId": 1,
    "productId": 1,
    "createdAt": "2024-05-23T17:20:35.236398"
}
```

### PUT `/api/v1/order_items/{order_item_id}`

- Description: Update an order item by id
- Headers:
```
  Authorization: Bearer token
```

- Request Body:
```json
{
    "quantity": 10
}
```

- Response 200

```json
{
    "id": 1,
    "quantity": 10,
    "orderId": 1,
    "productId": 1,
    "createdAt": "2024-05-23T17:20:35.236398"
}
```

### DELETE `/api/v1/order_items/{order_item_id}`

- Description: Delete an order item by id
- Headers:
```
  Authorization: Bearer token
```

- Response 200

```json
Order item successfully deleted.
```