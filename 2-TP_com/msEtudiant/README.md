# msEtudiant API (port 8081)

Base URL: http://localhost:8081/api/etudiant

Entities/DTOs
- Request body for create: EtudiantReqDto { nom, prenom, email, telephone, dateNaissance (YYYY-MM-DD), filiereId }
- Response: EtudiantResDto { id, nom, prenom, email, telephone, dateNaissance, filiere { id, titre, cycle } }

## Endpoints

### 1) Get étudiant by id
- Method: GET
- Path: `/{id}`
- Example: GET http://localhost:8081/api/etudiant/1
- Success 200 response (example):
```json
{
  "id": 1,
  "nom": "Doe",
  "prenom": "John",
  "email": "john.doe@example.com",
  "telephone": "0612345678",
  "dateNaissance": "2001-05-20",
  "filiere": {
    "id": 2,
    "titre": "Informatique",
    "cycle": "INGENIERIE"
  }
}
```

### 2) Create a new étudiant
- Method: POST
- Path: `/add`
- Headers: `Content-Type: application/json`
- Body (JSON) example:
```json
{
  "nom": "Doe",
  "prenom": "Jane",
  "email": "jane.doe@example.com",
  "telephone": "0699988877",
  "dateNaissance": "2002-11-15",
  "filiereId": 2
}
```
- Success 200 response (example):
```json
{
  "id": 5,
  "nom": "Doe",
  "prenom": "Jane",
  "email": "jane.doe@example.com",
  "telephone": "0699988877",
  "dateNaissance": "2002-11-15",
  "filiere": {
    "id": 2,
    "titre": "Informatique",
    "cycle": "INGENIERIE"
  }
}
```

Notes:
- `dateNaissance` must be ISO date (YYYY-MM-DD).
- `filiereId` must reference an existing filière in msFilier.

### 3) List étudiants (paged + filters)
- Method: GET
- Path: `/`
- Query params (all optional): `nom`, `prenom`, `email`, `filiereId`, plus pageable `page`, `size`, `sort`
- Example: GET http://localhost:8081/api/etudiant?page=0&size=10&nom=doe
- Success 200 response: Spring Data `Page` of étudiants (entity shape)

### 4) Update étudiant (full)
- Method: PUT
- Path: `/{id}`
- Body: same as create body

### 5) Patch étudiant (partial)
- Method: PATCH
- Path: `/{id}`
- Body example (any subset of fields):
```json
{ "email": "new.email@example.com" }
```

### 6) Delete étudiant
- Method: DELETE
- Path: `/{id}`

### 7) Transfer étudiant to another filière
- Method: POST
- Path: `/{id}/transfer?toFiliereId=2`
- Success 200 response: updated étudiant with new `filiere`

### 8) List étudiants by filière
- Method: GET
- Path: `/filiere/{idFiliere}`
- Example: GET http://localhost:8081/api/etudiant/filiere/2
- Success 200 response (example):
```json
[
  {
    "id": 1,
    "nom": "Doe",
    "prenom": "John",
    "email": "john.doe@example.com",
    "telephone": "0612345678",
    "dateNaissance": "2001-05-20",
    "filiereId": 2
  },
  {
    "id": 5,
    "nom": "Doe",
    "prenom": "Jane",
    "email": "jane.doe@example.com",
    "telephone": "0699988877",
    "dateNaissance": "2002-11-15",
    "filiereId": 2
  }
]
```

### 9) List étudiants by filière (paged)
- Method: GET
- Path: `/filiere/{idFiliere}/page`
- Query params: `page`, `size`, `sort`

### 10) Get filière of an étudiant
- Method: GET
- Path: `/{id}/filiere`
- Success 200 response (example):
```json
{ "id": 2, "titre": "Informatique", "cycle": "INGENIERIE" }
```

### 11) Stats: étudiants count by filière
- Method: GET
- Path: `/stats/by-filiere`
- Success 200 response (example):
```json
[
  { "filiereId": 1, "count": 12 },
  { "filiereId": 2, "count": 8 }
]
```

## Postman quick setup
- Create a collection named "msEtudiant" with the three requests above.
- Set a collection-level variable `baseUrl` = `http://localhost:8081/api/etudiant` (optional), then use `{{baseUrl}}` in request URLs.
- Typical error responses include a message because `server.error.include-message=always` is enabled.
