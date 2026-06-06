# Chakray_Diego_Vazquez

REST API developed with:

- Java 21
- Spring Boot
- Spring Security
- OpenAPI
- Lombok
- JUnit
- Docker

for the Chakray technical test.

# Requirements
- Java 21
- Maven 3.6.3+
- Docker & Docker Compose (optional)

# Run locally

./mvnw spring-boot:run

The API will be available at: http://localhost:8080

# Run with Docker

docker compose up --build

# API Endpoints

## Users

| Method | Endpoint                    | Description                                                            |
|--------|-----------------------------|------------------------------------------------------------------------|
| GET    | /users                      | Get all users                                                          |
| GET    | /users?sortedBy=name        | Get users sorted by field (email, id, name, phone, tax_id, created_at) |
| GET    | /users?filter=name+co+Diego | Filter users (co, eq, sw, ew)                                          |
| POST   | /users                      | Create a new user                                                      |
| PATCH  | /users/{id}                 | Update a user by ID                                                    |
| DELETE | /users/{id}                 | Delete a user by ID                                                    |

## Authentication

| Method | Endpoint | Description                           |
|--------|----------|---------------------------------------|
| POST   | /login   | Authenticate with tax_id and password |

## Docs

| URL                                   | Description |
|---------------------------------------|-------------|
| http://localhost:8080/swagger-ui.html | Swagger UI  |

# Test credentials

| tax_id        | password    |
|---------------|-------------|
| VASD950101ABC | password123 |
| VESL920202DEF | wordpass123 |
| DOAJ920202DEF | password321 |

# Login request example

POST /login
{
  "taxId": "VASD950101ABC",
  "password": "password123"
}

# Filter examples
GET /users?filter=name+co+Diego
GET /users?filter=email+ew+gmail.com
GET /users?filter=phone+sw+555
GET /users?filter=tax_id+eq+VASD950101ABC

# Pending
- AndresFormat phone validation: awaiting clarification from evaluators

# Author

Diego Vázquez
vazquezsotelodiego@gmail.com