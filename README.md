# Quiz Management API
Quiz Management API is a backend service for creating, managing and scoring quizzes. The API supports various quiz formats and includes endpoints for managing quizzes and user responses. It offers robust authentication and is easily extendable for additional features.

## Description
Quiz Management API is your one-stop, robust solution for creating, managing, scoring quizzes and handling user responses. This highly flexible and versatile API is designed to support a wide range of quiz applications and formats.

## Key Features

- Full CRUD functionality for quizzes and responses
- Support for multiple quiz formats (multiple-choice, true or false, single answer, descriptive answers)
- Robust authentication and authorization checks
- Easily extendable for new features

## Getting Started
These instructions will get a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites
- A text editor or an IDE (like Eclipse)
- JDK version 8 or later
- Maven for dependency management
- MySQL Server for the database
- Git

### Installing & Running
1. Clone the project from the GitHub repository
    ```bash
    git clone [repositoryUrl]
    ```
2. Navigate into the newly created project directory
3. Start the Spring Boot application
    ```bash
    ./mvnw spring-boot:run
    ```

Your Quiz Management API is now up and running, and ready to receive requests on http://localhost:8080.

## Testing
This API provides a suite of unit tests for testing all features. Run the following command to execute the tests.
```bash
    ./mvnw test
