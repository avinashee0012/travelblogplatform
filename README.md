# Travel Blog Platform

A Spring Boot–based backend application for a **Travel Blog Platform**, built to demonstrate **real-world backend engineering practices** using modern Java and Spring technologies.

The platform supports **JWT-based authentication**, **role-based access control (RBAC)**, clean **RESTful APIs**, and **admin moderation workflows**. Public users can browse blogs and comments, while authenticated users can contribute content based on their roles. The system is fully documented using **Swagger (OpenAPI)**.

> ⚠️ **Note**
> This project focuses on backend design, security, and API architecture.
> Automated tests (JUnit / Mockito) and Postman collections will be added later.
> TDD was **not followed** during the initial implementation.

---

## Tech Stack

* Java 17
* Spring Boot 3
* Spring MVC
* Spring Data JPA
* Spring Security
* JWT (stateless authentication)
* MySQL
* Maven
* Swagger / OpenAPI (springdoc)
* Logback

**Intentionally not used:** Lombok (for learning clarity)

---

## Architecture Overview

* Layered MVC architecture
* Stateless authentication using JWT
* Role-based authorization (RBAC)
* DTO-based request/response mapping
* Centralized exception handling
* Public vs secured API separation

---

## Roles & Permissions (RBAC)

| Role   | Capabilities                                       |
| ------ | -------------------------------------------------- |
| USER   | Read blogs, add comments                           |
| AUTHOR | Create, edit, delete own blogs, add comments       |
| ADMIN  | Moderate and manage all blogs, users, and comments |

RBAC is enforced using:

* Path-based security (Spring Security)
* Method-level security (`@PreAuthorize`)

---

## API Overview

### Authentication APIs

| Method | Endpoint             | Description           |
| ------ | -------------------- | --------------------- |
| POST   | `/api/auth/register` | Register a new user   |
| POST   | `/api/auth/login`    | Login and receive JWT |

---

### Public APIs (No Authentication Required)

| Method | Endpoint                        | Description                           |
| ------ | ------------------------------- | ------------------------------------- |
| GET    | `/api/public`                   | Get paginated list of published blogs |
| GET    | `/api/public/{slug}`            | Get a single blog by slug             |
| GET    | `/api/public/{blogId}/comments` | Get comments for a blog               |

These endpoints are intentionally public for:

* Read-only access
* SEO friendliness
* Better user experience

---

### Blog APIs (Authenticated)

| Method | Endpoint                  | Role              |
| ------ | ------------------------- | ----------------- |
| POST   | `/api/blogs`              | AUTHOR            |
| PUT    | `/api/blogs/{id}`         | AUTHOR (own blog) |
| DELETE | `/api/blogs/{id}`         | AUTHOR (own blog) |
| PATCH  | `/api/blogs/{id}/publish` | AUTHOR            |

---

### Comment APIs (Authenticated)

| Method | Endpoint                          | Role          |
| ------ | --------------------------------- | ------------- |
| POST   | `/api/blogs/{blogId}/comments`    | USER / AUTHOR |
| DELETE | `/api/blogs/comments/{commentId}` | Owner / ADMIN |

---

### Admin APIs (ADMIN Only)

| Method | Endpoint                                        | Description              |
| ------ | ----------------------------------------------- | ------------------------ |
| PATCH  | `/api/admins/users/{userId}/make-author`        | Promote user to AUTHOR   |
| PATCH  | `/api/admins/users/{userId}/make-admin`         | Promote user to ADMIN    |
| PATCH  | `/api/admins/blogs/{blogId}/status/need-review` | Mark blog as NEED_REVIEW |
| PATCH  | `/api/admins/blogs/{blogId}/status/removed`     | Remove a blog            |
| PATCH  | `/api/admins/comments/{commentId}/hide`         | Hide a comment           |

---

## Blog Lifecycle

Blogs move through the following statuses:

* `DRAFT` – newly created blog
* `PUBLISHED` – visible to the public
* `NEED_REVIEW` – flagged by admin
* `REMOVED` – taken down by admin

Only **PUBLISHED** blogs appear in public APIs.

---

## Comment Model

* Each comment:

  * Belongs to **one blog**
  * Is written by **one user**
* No nested/threaded comments (intentional design choice)
* Admins can hide inappropriate comments
* Comment visibility is enforced at the service layer

---

## Swagger / OpenAPI

Swagger UI is enabled and publicly accessible.

* **Swagger UI:**
  `/public/swagger-ui/index.html`

* **OpenAPI JSON:**
  `/public/api-docs`

Swagger is the **source of truth** for API contracts in this project.

---

## Testing Status

* ❌ JUnit / Mockito tests: **Not implemented yet**
* ❌ TDD: **Not followed**
* ⏳ Postman collection: **Planned**
* ⏳ Unit & service tests: **Planned**

Testing will be added incrementally after stabilizing the API surface.

---

## Project Goals

This project is intended to demonstrate:

* Secure backend API design
* Practical RBAC implementation
* Clean separation of concerns
* Realistic admin moderation workflows
* Interview-ready backend architecture
