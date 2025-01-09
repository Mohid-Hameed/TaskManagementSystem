# Task Management System

## Overview

This project is a Kotlin-based Spring Boot application for managing tasks, projects, and users. It provides a REST API to handle CRUD operations for tasks, projects, and users while maintaining relationships between these entities.

The project uses PostgreSQL as its database and Docker Compose for setting up the database environment.

---

## Prerequisites

- [Kotlin](https://kotlinlang.org/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [PostgreSQL](https://www.postgresql.org/)
- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)

---

## Setup Instructions

### 1. Clone the Repository

```bash
git clone <repository_url>
cd TaskManagementSystem
```

### 2. Start the Database

Ensure Docker and Docker Compose are installed and running on your machine.

```bash
docker-compose up -d
```

This command starts a PostgreSQL container on port `5430` with the following credentials:

- **Username**: `postgres`
- **Password**: `postgres`
- **Database Name**: `postgres`

### 3. Configure Application

Modify the `application.properties` file (if required) to match your database credentials and port.

### 4. Run the Application

Use your preferred IDE or the command line to start the application:

```bash
./gradlew bootRun
```

The application will be available at `http://localhost:8080`.

---

## API Endpoints

### Users API

#### 1. Get All Users

- **Endpoint**: `GET /api/users`
- **Description**: Retrieve a list of all users.
- **Response**:

```json
[
    {
        "id": 1,
        "name": "User_1",
        "email": "user10@gmail.com",
        "createdAt": "2025-01-09T12:21:28.128431",
        "tasks": [
            {
                "id": 1,
                "title": "Task_1_P1",
                "description": "Task Description",
                "status": "IN PROGRESS",
                "priority": 1,
                "createdAt": "2025-01-09T12:37:31.84011",
                "updatedAt": "2025-01-09T12:37:31.840143"
            }
        ],
        "projects": [
            {
                "id": 1,
                "name": "Project_1",
                "description": "Project Description for the first project",
                "createdAt": "2025-01-09T12:34:14.806581",
                "updatedAt": "2025-01-09T12:34:14.806742"
            }
        ]
    },
]
```

#### 2. Get User by ID

- **Endpoint**: `GET /api/users/{id}`
- **Description**: Retrieve details of a specific user by their ID.
- **Response**: Similar to `Get All Users`.

#### 3. Create a User

- **Endpoint**: `POST /api/users`
- **Request Body**:

```json
{
    "name": "User_2",
    "email": "user20@gmail.com",  
}
```

- **Response**: Created user object.

#### 4. Update a User

- **Endpoint**: `PUT /api/users/{id}`
- **Request Body**:

```json
{
  "name": "John Doe Updated",
  "email": "updated.email@example.com"
}

OR

{
  "name": "John Doe Updated"
}

OR

{
  "email": "updated.email@example.com"
}
```

- **Response**: Updated user object.

#### 5. Delete a User

- **Endpoint**: `DELETE /api/users/{id}`
- **Response**: Status `200 OK` on success.

---

### Projects API

#### 1. Get All Projects

- **Endpoint**: `GET /api/projects`
- **Description**: Retrieve a list of all projects.
- **Response**: Array of project objects.

#### 2. Get Project by ID

- **Endpoint**: `GET /api/projects/{id}`
- **Response**: Project object with related tasks and manager information.

#### 3. Create a Project

- **Endpoint**: `POST /api/projects`
- **Request Body**:

```json
{
  "name": "New Project",
  "description": "Project description",
  "managerId": 1
}
```

- **Response**: Created project object.

#### 4. Update a Project

- **Endpoint**: `PUT /api/projects/{id}`
- **Request Body**:

```json
{
  "name": "Updated Project",
  "description": "Updated description"
}
```

- **Response**: Updated project object.

#### 5. Delete a Project

- **Endpoint**: `DELETE /api/projects/{id}`
- **Response**: Status `200 OK` on success.

---

### Tasks API

#### 1. Get All Tasks

- **Endpoint**: `GET /api/tasks`
- **Response**: Array of task objects sorted by priority.

#### 2. Get Task by ID

- **Endpoint**: `GET /api/tasks/{id}`
- **Response**: Task object with user and project details.

#### 3. Create a Task

- **Endpoint**: `POST /api/tasks`
- **Request Body**:

```json
{
  "title": "New Task",
  "description": "Task description",
  "status": "PENDING",
  "priority": 1,
  "userId": 1,
  "projectId": 1
}
```

- **Response**: Created task object.

#### 4. Update a Task

- **Endpoint**: `PUT /api/tasks/{id}`
- **Request Body**:

```json
{
  "title": "Updated Task",
  "description": "Updated description",
  "status": "COMPLETED",
  "priority": 2
}
```

- **Response**: Updated task object.

#### 5. Assign Task to Project

- **Endpoint**: `PUT /api/tasks/{taskId}/assign/project/{projectId}`
- **Response**: Task object with updated project.

#### 6. Assign Task to User

- **Endpoint**: `PUT /api/tasks/{taskId}/assign/user/{userId}`
- **Response**: Task object with updated user.

#### 7. Delete a Task

- **Endpoint**: `DELETE /api/tasks/{id}`
- **Response**: Status `200 OK` on success.

---

## Docker Compose Configuration

The `docker-compose.yml` file sets up a PostgreSQL database:

```yaml
version: '3.7'

services:
  task_management_db:
    container_name: task_management_db
    image: postgres:12
    ports:
      - 5430:5432
    environment:
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres
      POSTGRES_DB: postgres
    volumes:
      - pgdata:/var/lib/postgresql/data
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U postgres"]
      interval: 10s
      timeout: 5s
      retries: 5
    restart: always
volumes:
  pgdata: {}
```

---

## Key Classes and Data Models

### Tasks

- **Attributes**:
  - `id`: Task ID.
  - `title`: Task title.
  - `description`: Task description.
  - `status`: Task status (`PENDING`, `IN_PROGRESS`, `COMPLETED`).
  - `priority`: Task priority.
  - `createdAt`: Task creation timestamp.
  - `updatedAt`: Task last updated timestamp.
  - `user`: Associated user.
  - `project`: Associated project.

### DTOs (Data Transfer Objects)

- **CreateTaskDTO**: Handles task creation.
- **UpdateUserDTO**: Handles user updates.
- **CreateProjectDTO**: Handles project creation.

---

## Testing

Use tools like Postman or cURL to test the endpoints. Ensure the database is running and the application is started before testing.

Example cURL request:

```bash
curl -X POST http://localhost:8080/api/tasks \
-H "Content-Type: application/json" \
-d '{
  "title": "New Task",
  "description": "Task description",
  "userId": 1,
  "projectId": 1
}'
```

---

## Notes

- All task updates automatically update the `updatedAt` field to the current timestamp.
- The database schema is auto-generated based on JPA annotations.
- Tasks, projects, and users are interrelated, so deleting entities may require handling cascading relationships in the database.


