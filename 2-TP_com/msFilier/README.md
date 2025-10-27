# msFilier API (port 8080)

Base URL: http://localhost:8080/api/filiere

DTOs
- FiliereResp { id, titre, cycle }
- EtudiantsDeFiliereDTO { id, titre, cycle, etudiants: EtudiantDTO[] }
- EtudiantDTO { id, nom, prenom, email, telephone, dateNaissance, filiereId }

## Endpoints

### 1) List filières (paged)
- Method: GET
- Path: `/`
- Query params: `page`, `size`, `sort`
- Example: GET http://localhost:8080/api/filiere?page=0&size=10

### 2) Create filière
- Method: POST
- Path: `/`
- Body example:
```json
{ "titre": "Informatique", "cycle": "INGENIERIE" }
```

### 3) Get filière by id
- Method: GET
- Path: `/{id}`
- Example: GET http://localhost:8080/api/filiere/2
- Success 200 response (example):
```json
{
  "id": 2,
  "titre": "Informatique",
  "cycle": "INGENIERIE"
}
```

### 4) Get filière with its étudiants
- Method: GET
- Path: `/etudiants/{idFiliere}`
- Example: GET http://localhost:8080/api/filiere/etudiants/2
- Success 200 response (example):
```json
{
  "id": 2,
  "titre": "Informatique",
  "cycle": "INGENIERIE",
  "etudiants": [
    {
      "id": 1,
      "nom": "Doe",
      "prenom": "John",
      "email": "john.doe@example.com",
      "telephone": "0612345678",
      "dateNaissance": "2001-05-20",
      "filiereId": 2
    }
  ]
}
```

Notes:
- `cycle` enum values: LICENCE, INGENIERIE, MASTER, DOCTORAT.
- This service exposes only read endpoints in the current codebase.

### 5) Update filière (full)
- Method: PUT
- Path: `/{id}`
- Body: same as create body

### 6) Patch filière (partial)
- Method: PATCH
- Path: `/{id}`
- Query params: `titre` and/or `cycle`

### 7) Delete filière
- Method: DELETE
- Path: `/{id}`

## Postman quick setup
- Create a collection named "msFilier" with the two GET requests above.
- Set a collection-level variable `baseUrl` = `http://localhost:8080/api/filiere` (optional), then use `{{baseUrl}}` in request URLs.
