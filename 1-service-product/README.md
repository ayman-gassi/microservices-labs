# 1-service-product

Spring Boot microservice for managing products.

## Tech Stack
- Java
- Spring Boot
- Maven (wrapper provided)

## Project Structure
- `src/main/java/com/product/serviceproduct` — Application source
- `src/main/resources` — Configuration and templates

## Run (Windows PowerShell)
From this folder:
```powershell
.\mvnw.cmd spring-boot:run
```

## Build
```powershell
.\mvnw.cmd clean package
```

## Configuration
Edit `src/main/resources/application.properties` to change server port, DB, etc.

## Endpoints

- Base URL: `http://localhost:8080/api/products`

- GET `/api/products`
	- Description: List all products
	- Response: `200 OK` with `ProductRespDTO[]`

- GET `/api/products/{id}`
	- Description: Get a product by id
	- Response: `200 OK` with `ProductRespDTO`

- POST `/api/products`
	- Description: Create a product
	- Body (JSON): `ProductReqDTO`
	- Response: `201 Created` with `ProductRespDTO`

- PUT `/api/products/{id}`
	- Description: Update a product
	- Body (JSON): `ProductReqDTO`
	- Response: `200 OK` with `ProductRespDTO`

- DELETE `/api/products/{id}`
	- Description: Delete a product
	- Response: `204 No Content`

### DTOs

- ProductReqDTO (request):
	- `name` (string)
	- `description` (string)
	- `prix` (number)
	- `prixUsine` (number)
	- `stock` (number)

- ProductRespDTO (response):
	- `id` (number)
	- `name` (string)
	- `description` (string)
	- `prix` (number)
	- `prixUsine` (number)
	- `stock` (number)

## Test with Postman

Set the request header `Content-Type: application/json` for POST/PUT.

1) Create Product
- Method: POST
- URL: `http://localhost:8080/api/products`
- Body (raw JSON):
```
{
	"name": "Keyboard",
	"description": "Mechanical keyboard",
	"prix": 499.0,
	"prixUsine": 300.0,
	"stock": 25
}
```
- Expect: `201 Created` with created product JSON (includes `id`)

2) Get All Products
- Method: GET
- URL: `http://localhost:8080/api/products`
- Expect: `200 OK` with a JSON array

3) Get Product by ID
- Method: GET
- URL: `http://localhost:8080/api/products/1`
- Expect: `200 OK` with a single JSON object

4) Update Product
- Method: PUT
- URL: `http://localhost:8080/api/products/1`
- Body (raw JSON):
```
{
	"name": "Keyboard Pro",
	"description": "Mechanical keyboard with RGB",
	"prix": 599.0,
	"prixUsine": 320.0,
	"stock": 20
}
```
- Expect: `200 OK` with updated product

5) Delete Product
- Method: DELETE
- URL: `http://localhost:8080/api/products/1`
- Expect: `204 No Content`
