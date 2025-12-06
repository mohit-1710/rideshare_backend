# RideShare Backend

A robust and scalable backend service for a ride-sharing application, built with Spring Boot and MongoDB. This project implements secure authentication, real-time ride management, and role-based access control for passengers and drivers.

## Key Features

*   **Secure Authentication**: JWT-based stateless authentication with BCrypt password hashing.
*   **Role-Based Access**: Distinct functionality for Passengers (`ROLE_USER`) and Drivers (`ROLE_DRIVER`).
*   **Ride Management**: Complete lifecycle management - Request, Accept, and Complete trips.
*   **Data Validation**: Robust input validation to ensure data integrity.
*   **Scalable Architecture**: Built on a clean, service-oriented architecture using Spring Boot best practices.

## Tech Stack

*   **Framework**: Spring Boot 3+
*   **Database**: MongoDB
*   **Security**: Spring Security & JWT (JSON Web Tokens)
*   **Build Tool**: Maven
*   **Language**: Java 17+

## Setup & Installation

1.  **Prerequisites**:
    *   Java Development Kit (JDK) 17 or higher
    *   MongoDB installed and running locally on port `27017`
    *   Maven (optional, wrapper included)

2.  **Clone the Repository**:
    ```bash
    git clone https://github.com/yourusername/rideshare_backend.git
    cd rideshare_backend
    ```

3.  **Configuration**:
    The application is pre-configured to run on port `8081`. You can modify `src/main/resources/application.properties` if needed.

4.  **Build and Run**:
    ```bash
    ./mvnw spring-boot:run
    ```

## API Documentation

### Authentication
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/auth/register` | Register a new user (Passenger or Driver) |
| `POST` | `/api/auth/login` | Login and receive a JWT access token |

### Trip Management (Passenger)
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/v1/rides` | Request a new trip |
| `GET` | `/api/v1/user/rides` | View your trip history |

### Trip Management (Driver)
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/api/v1/driver/rides/requests` | View pending trip requests |
| `POST` | `/api/v1/driver/rides/{id}/accept` | Accept a trip request |

### General
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/v1/rides/{id}/complete` | Mark a trip as completed |

## Testing

You can test the APIs using **Postman** or **cURL**.

**Example: Register a User**
```bash
curl -X POST http://localhost:8081/api/auth/register \
-H "Content-Type: application/json" \
-d '{"username":"john_doe","password":"password123","role":"ROLE_USER"}'
```

**Example: Login**
```bash
curl -X POST http://localhost:8081/api/auth/login \
-H "Content-Type: application/json" \
-d '{"username":"john_doe","password":"password123"}'
```

## Project Structure

```
com.ridehub.backend
├── config       # Security and App Configuration
├── controller   # REST API Controllers
├── dto          # Data Transfer Objects
├── exception    # Global Exception Handling
├── model        # MongoDB Entities (Account, TripRequest)
├── repository   # Data Access Layer
├── service      # Business Logic
└── util         # Utilities (JWT, etc.)
```
