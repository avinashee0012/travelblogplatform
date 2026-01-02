# Travel Blog Platform

This project is a Spring Boot–based backend for a Travel Blog Platform, designed to demonstrate real-world backend development practices using modern Java technologies. The application supports secure user authentication with JWT, role-based access control (RBAC), and a clean RESTful API architecture. Users can read travel blogs publicly, while authenticated authors can create and manage their own blog posts. Administrators have moderation capabilities to approve, reject, or remove content, ensuring platform quality and safety. The system follows a layered MVC architecture, uses Spring Data JPA with MySQL for persistence, and is documented using Swagger (OpenAPI). Emphasis is placed on clean code, clear domain modeling, and testability, with unit tests written using JUnit 5 and Mockito and a TDD-friendly workflow.

## Tech Stack

Java 17, OOP, Spring Boot 3, Spring MVC, Spring Data JPA, MySQL, Spring Security, JWT, Maven, Git, Swagger (OpenAPI), Logback, JUnit 5, Mockito, TDD

---

## Core Features

1. User & Authentication
    - User registration
    - User login
    - JWT-based authentication
    - Role-based access control (RBAC)

    **Roles & Permissions**

    | Role     | Capabilities                                 |
    | -------- | -------------------------------------------- |
    | `USER`   | Read blogs, add comments                     |
    | `AUTHOR` | Create, edit, delete own blogs, add comments |
    | `ADMIN`  | Moderate and manage all blogs and comments   |

2. Blog Management
    - Create a travel blog
    - Edit own blog
    - Delete own blog
    - View all published blogs
    - View a single blog
    - Blog status management:
        * `DRAFT`
        * `PUBLISHED`
        * `REJECTED`

    Only published blogs are visible to the public.

3. Admin Capabilities
    - View all blogs (including drafts and rejected)
    - Approve or reject blogs
    - Delete any blog
    - Moderate inappropriate content

4. Categories
    - Each blog belongs to one category
    - Categories are predefined (e.g., Beach, Mountains, City, Adventure)
    - Only admins manage categories
    - Authors select from existing categories

5. Content Management
    - Authenticated users can add comments on blogs
    - Each comment:
        * Belongs to one blog
        * Is written by one user
    - Users can edit or delete their own comments
    - Admins can moderate or hide any comment
    - Comment status:
        * `VISIBLE`
        * `HIDDEN`

    No nested or threaded comments (intentional for simplicity).

