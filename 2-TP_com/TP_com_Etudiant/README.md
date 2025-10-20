# TP_com_Etudiant

Spring Boot microservice for managing students (Etudiants).

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

- Base URL: `http://localhost:8081/api/etudiants`

- GET `/api/etudiants`
	- Description: List all students
	- Response: `200 OK` with `EtudiantResDto[]`

- GET `/api/etudiants/{id}`
	- Description: Get a student by id
	- Response: `200 OK` with `EtudiantResDto`

- POST `/api/etudiants`
	- Description: Create a student
	- Body (JSON): `EtudiantReqDto`
	- Response: `201 Created` with `EtudiantResDto`

- PUT `/api/etudiants/{id}`
	- Description: Update a student
	- Body (JSON): `EtudiantReqDto`
	- Response: `200 OK` with `EtudiantResDto`

- DELETE `/api/etudiants/{id}`
	- Description: Delete a student
	- Response: `204 No Content`

### DTOs

- EtudiantReqDto (request):
	- `nom` (string)
	- `prenom` (string)
	- `email` (string)
	- `telephone` (string)
	- `filiereId` (number)

- EtudiantResDto (response):
	- `id` (number)
	- `nom` (string)
	- `prenom` (string)
	- `email` (string)
	- `telephone` (string)
	- `filiere` (object: `FiliereDto` with `id`, `code`, `intitule`, `description`, `cycle`)

## Test with Postman

Set the request header `Content-Type: application/json` for POST/PUT.

1) Create Etudiant
- Method: POST
- URL: `http://localhost:8081/api/etudiants`
- Body (raw JSON):
```
{
	"nom": "Doe",
	"prenom": "John",
	"email": "john.doe@example.com",
	"telephone": "+212600000000",
	"filiereId": 1
}
```
- Expect: `201 Created` and the created student with an `id` and `filiere` details

2) Get All Etudiants
- Method: GET
- URL: `http://localhost:8081/api/etudiants`
- Expect: `200 OK` with a JSON array

3) Get Etudiant by ID
- Method: GET
- URL: `http://localhost:8081/api/etudiants/1`
- Expect: `200 OK` with a single JSON object

4) Update Etudiant
- Method: PUT
- URL: `http://localhost:8081/api/etudiants/1`
- Body (raw JSON):
```
{
	"nom": "Doe",
	"prenom": "Jane",
	"email": "jane.doe@example.com",
	"telephone": "+212611111111",
	"filiereId": 1
}
```
- Expect: `200 OK` with updated student

5) Delete Etudiant
- Method: DELETE
- URL: `http://localhost:8081/api/etudiants/1`
- Expect: `204 No Content`

### Notes
- Ensure the Filiere with `id` used in `filiereId` exists in the Filiere service.
- Default port for this service is `8081` (see `application.properties`).
