# Travel Blog Platform

A Spring Boot–based backend application for a **Travel Blog Platform**, built to demonstrate **real-world backend engineering practices** using modern Java and Spring technologies.

The platform supports **JWT-based authentication**, **role-based access control (RBAC)**, clean **RESTful APIs**, and **admin moderation workflows**. Public users can browse blogs and comments, while authenticated users can contribute content based on their roles. The system is fully documented using **Swagger (OpenAPI)**.

---

## Tech Stack

* Java 17
* Spring Boot 3
* Spring Web
* Spring Data JPA
* Spring Security
* JWT (stateless authentication)
* MySQL
* Maven
* Swagger / OpenAPI (springdoc)

---

## Architecture Overview

* Layered architecture (Controller --> Service --> Repository)
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

---

## Swagger / OpenAPI

Swagger UI is enabled and publicly accessible.

* **Swagger UI:**
  `/public/swagger-ui/index.html`
