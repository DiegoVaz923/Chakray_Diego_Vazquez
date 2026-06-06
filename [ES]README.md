# Chakray_Diego_Vazquez

API REST desarrollada con:

- Java 21
- Spring Boot
- Spring Security
- OpenAPI
- Lombok
- JUnit
- Docker

para la prueba técnica de Chakray.

# Requerimientos
- Java 21
- Maven 3.6.3+
- Docker & Docker Compose (opcional)

# Ejecutar en local

./mvnw spring-boot:run

La API estará disponible en la URL: http://localhost:8080

# Ejecutar con Docker

docker compose up --build

# Endpoints de la API

## Usuarios

| Método | Endpoint                    | Descripción                                                                                  |
|--------|-----------------------------|----------------------------------------------------------------------------------------------|
| GET    | /users                      | Obten todos los usuarios                                                                     |
| GET    | /users?sortedBy=name        | Obten todos los usuarios ordenados por un campo (email, id, name, phone, tax_id, created_at) |
| GET    | /users?filter=name+co+Diego | Filtrar usuarios (co, eq, sw, ew)                                                            |
| POST   | /users                      | Crear un nuevo usuario                                                                       |
| PATCH  | /users/{id}                 | Actualizar un usuario por ID                                                                 |
| DELETE | /users/{id}                 | Eliminar un usuario por ID                                                                   |

## Autenticación

| Método | Endpoint | Descripción                         |
|--------|----------|-------------------------------------|
| POST   | /login   | Autenticación con tax_id y password |

## Docs

| URL                                   | Descripción   |
|---------------------------------------|---------------|
| http://localhost:8080/swagger-ui.html | UI de Swagger |

# Credenciales de prueba

| tax_id        | password    |
|---------------|-------------|
| VASD950101ABC | password123 |
| VESL920202DEF | wordpass123 |
| DOAJ920202DEF | password321 |

# Ejemplo de petición de Login

POST /login
{
"taxId": "VASD950101ABC",
"password": "password123"
}

# Ejemplos de filtros
GET /users?filter=name+co+Diego
GET /users?filter=email+ew+gmail.com
GET /users?filter=phone+sw+555
GET /users?filter=tax_id+eq+VASD950101ABC

# Pendiente
- Validación "AndresFormat" para número telefonico: En espera de respuesta de la evaluadora

# Autor

Diego Vázquez
vazquezsotelodiego@gmail.com