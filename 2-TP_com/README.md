# Microservices: Filières & Étudiants

This workspace contains two Spring Boot microservices that manage university filières and students.

- msFilier (port 8080): Filière read APIs
- msEtudiant (port 8081): Étudiant CRUD-lite APIs

Both services use MySQL locally:
- msFilier → jdbc:mysql://localhost:3306/filieredb (user: root, password: empty)
- msEtudiant → jdbc:mysql://localhost:3306/etudiantdb (user: root, password: empty)

## How to run (optional)
- Start MySQL and create databases `filieredb` and `etudiantdb`.
- Run each service from its folder with your IDE or `mvnw spring-boot:run`.

## API routes summary

Base URLs:
- msFilier: http://localhost:8080/api/filiere
- msEtudiant: http://localhost:8081/api/etudiant

### msFilier (8080)
- GET / → List filières (paged)
- POST / → Create filière
- GET /{id} → Get one filière by id
- PUT/PATCH /{id} → Update/patch filière
- DELETE /{id} → Delete filière
- GET /etudiants/{idFiliere} → Get filière details plus all its students (aggregated)

Details and examples: see [msFilier/README.md](./msFilier/README.md)

### msEtudiant (8081)
- GET / → List étudiants (filters + pageable)
- GET /{id} → Get one étudiant by id
- POST /add or POST / → Create a new étudiant
- PUT/PATCH /{id} → Update/patch étudiant
- DELETE /{id} → Delete étudiant
- POST /{id}/transfer?toFiliereId= → Transfer étudiant to another filière
- GET /filiere/{idFiliere} → List all étudiants in a filière
- GET /filiere/{idFiliere}/page → Paged étudiants in a filière
- GET /{id}/filiere → Get filière of a specific étudiant
- GET /stats/by-filiere → Count étudiants grouped by filière

Details and examples: see [msEtudiant/README.md](./msEtudiant/README.md)

## Postman testing
Import the endpoints described in each service README. Example bodies and typical responses are included to speed up testing.
