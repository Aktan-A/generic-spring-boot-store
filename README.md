# neobis-week3

## Project Setup
```bash
git clone git@github.com:Aktan-A/neobis-week3.git
./mvnw clean spring-boot:run
```

## Endpoints:

**Description:** Create a product

**Path: POST** `/api/v1/products`

**Parameters:** N/A

**Response:** 201

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

**Description:** Get product details

**Path: GET** `/api/v1/products/{product_id}`

**Parameters:** N/A

**Response:** 200

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

**Description:** Update product details

**Path: PUT** `/api/v1/products/{product_id}`

**Parameters:** N/A

**Response:** 200

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

**Description:** Get product list

**Path: GET** `/api/v1/products`

**Parameters:** N/A

**Response:** 200

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

**Description:** Delete a product

**Path: DELETE** `/api/v1/products/{product_id}`

**Parameters:** N/A

**Response:** 204