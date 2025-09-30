# 📚 Shelf of Shame – Backend

The backend service for **Shelf of Shame** — a reading companion app to help you manage, track, and finally read the books piling up on your shelf 📖. It powers user authentication, book management, reading progress tracking, and personalized recommendations.

---

## ✨ Features

* 🔐 JWT-based authentication
* 📘 CRUD operations for books on a personal shelf
* 📝 Track reading status, notes, and perceived difficulty
* 💾 Persistent storage in PostgreSQL (`ddl-auto: update`)
* 📜 Swagger/OpenAPI documentation for exploring all endpoints

---

## 🛠️ Tech Stack

* ☕ Java 21 + Spring Boot
* 🐘 PostgreSQL
* 📄 Spring Data JPA
* 📑 Swagger/OpenAPI
* 🔐 Spring Security
* 🧪 H2 database for testing

---

## 📦 Prerequisites

Before starting, make sure you have:

* ☕ **Java 21+** installed
* 🧰 **Maven** installed
* 🐘 **PostgreSQL** running locally on port 5433 with a database named `shelf`
* ⚡ Optional: H2 database is used in tests (no setup required for normal operation)

---

## ⚙️ Configuration

### 1️⃣ **JWT Secrets**

The backend uses a `.env` file for JWT configuration. Create a `.env` file in the project root with the following contents:

```env
JWT_SECRET=your_jwt_secret
JWT_EXPIRATION_MS=3600000
```

### 2️⃣ **Database and Server Settings**

Configure the server and PostgreSQL database in `src/main/resources/application.yml`:

```yaml
server:
  port: 8080
  error:
    include-message: always
    include-binding-errors: always

spring:
  datasource:
    url: jdbc:postgresql://localhost:5433/shelf
    password:
  jpa:
    hibernate:
      ddl-auto: update
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
        format_sql: true
    show-sql: true
```

Notes: PostgreSQL database is `shelf` on `localhost:5433`. Leave the password blank if using the default user. Schema updates automatically on restart (`ddl-auto: update`). JWT secret and expiration are read from the `.env` file.

---

## ▶️ Run the Backend

Start the server with:

```bash
mvn spring-boot:run
```

Server will run at [http://localhost:8080](http://localhost:8080) 🚀

---

## 📡 API Documentation

Swagger UI is available at [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html), where you can explore all available endpoints and DTOs.

---

## 🧠 Motivation

Inspired by the Japanese concept of **tsundoku (積ん読)** — acquiring books but never reading them — **Shelf of Shame** 📚 helps readers organize, track, and finally read their personal collections. It’s designed to turn your pile of unread books into a source of enjoyment!
