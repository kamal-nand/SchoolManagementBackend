# School Management API (Spring Boot + PostgreSQL)

**Backend Required Tech**: Spring Boot 3, PostgreSQL, Spring Data JPA, Validation, Spring Security (JWT), Swagger/OpenAPI, Unit & Integration tests.

## Requirements
- Java 21 (or 17+)
- Maven 3.9+
- PostgreSQL

> Update `src/main/resources/application.yml` if your DB credentials differ.

## Run
```bash
mvn spring-boot:run
```

Swagger UI: http://localhost:8080/swagger-ui.html

## Quickstart
1. **Register a user** (e.g., admin):
   ```bash
   curl -X POST "http://localhost:8080/api/auth/register?username=admin&password=secret&role=ADMIN"
   ```
2. **Login** to get JWT:
   ```bash
   curl -X POST "http://localhost:8080/api/auth/login" -H "Content-Type: application/json" -d '{"username":"admin","password":"secret"}'
   ```
   Copy the `token` value.
3. **Call secured endpoints** with the token:
   ```bash
   curl -H "Authorization: Bearer <TOKEN>" http://localhost:8080/api/students
   ```

## Entities
- `User` (username, password[bcrypt], role: ADMIN/TEACHER/STUDENT)
- `Student` (firstName, lastName, email)
- `Course` (code, title, description)
- `Enrollment` (student, course, enrolledAt)

## Endpoints (selection)
- `POST /api/auth/register` (public)
- `POST /api/auth/login` (public)
- `GET/POST/PUT/DELETE /api/students` (role: ADMIN/TEACHER for write; all roles can read)
- `GET/POST/PUT/DELETE /api/courses` (role: ADMIN/TEACHER for write; all roles can read)
- `POST /api/enrollments` (ADMIN/TEACHER), `GET /api/enrollments` (all roles)

## Tests
- Unit tests for service layer (`StudentServiceTest`)
- Integration test for authentication flow (`AuthControllerIT`), uses H2 profile

---
