# TP_com_Filiere

Spring Boot microservice for managing filieres (programs/majors).

## Tech Stack
- Java
- Spring Boot
- Maven (wrapper provided)

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
Edit `src/main/resources/application.properties` for port and datasource config.

## Endpoints

- Base URL: `http://localhost:8080/api/filiere`

- GET `/api/filiere/{id}`
	- Description: Get a filiere by id
	- Response: `200 OK` with `Filiere` entity

- POST `/api/filiere/add`
	- Description: Create a new filiere
	- Body (JSON): `FiliereDTO`
	- Response: `200 OK` with created `Filiere` entity

- GET `/api/filiere/`
	- Description: List filieres (paginated)
	- Query params: `page` (default 0), `size` (default 10), sort params supported by Spring Data
	- Response: `200 OK` with `Page<Filiere>` (JSON page object)

### DTOs

- FiliereDTO (request):
	- `code` (string)
	- `intitule` (string)
	- `description` (string)
	- `cycle` (enum: `LICENCE`, `MASTER`, `DOCTORAT`)

## Test with Postman

Set the request header `Content-Type: application/json` for POST.

1) Create Filiere
- Method: POST
- URL: `http://localhost:8080/api/filiere/add`
- Body (raw JSON):
```
{
	"code": "INFO",
	"intitule": "Informatique",
	"description": "Programme informatique",
	"cycle": "LICENCE"
}
```
- Expect: `200 OK` with the created filiere (id, code, intitule, description, cycle)

2) Get Filiere by ID
- Method: GET
- URL: `http://localhost:8080/api/filiere/1`
- Expect: `200 OK` with a single filiere

3) List Filieres (paginated)
- Method: GET
- URL: `http://localhost:8080/api/filiere/?page=0&size=10`
- Expect: `200 OK` with pageable JSON (content, totalElements, totalPages, number, size)

### Notes
- Default port for this service is `8080` (see `application.properties`).
- Ensure your database is reachable and schema is created via JPA `ddl-auto=update`.
