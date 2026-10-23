# Electronic Store

A full stack e-commerce app for selling electronics. Customers can browse products by category, add them to a cart and place orders. Admins get a dashboard to manage categories, products, orders and users.

- **Backend:** Java 17, Spring Boot 2.7, Spring Security with JWT, Spring Data JPA, MySQL
- **Frontend:** React 18, React Router, Context API, Axios, React Bootstrap

> I built this while following the Electronic Store course by Learn Code With Durgesh (LCWD), then cleaned it up so it runs anywhere with one command. The changes I made on top of the course code are listed [below](#what-i-changed-after-the-course).

## Features

**Customers**
- Sign up and log in with email and password (JWT)
- Browse the store and filter by category, with infinite scroll
- Cart that keeps items even before logging in, then place an order
- Order history and a profile page with photo upload

**Admin**
- Separate admin dashboard, only for users with the admin role
- Add, edit and delete categories and products (with images and a rich text description)
- See all orders and update order and payment status
- See all registered users

**API**
- REST endpoints for auth, users, categories, products, cart and orders
- Product and user search endpoints, and a Google sign-in endpoint
- Pagination and sorting on list endpoints
- Role based access (`ROLE_ADMIN`, `ROLE_NORMAL`)
- One global exception handler so errors come back in the same format
- Swagger UI at `http://localhost:9090/swagger-ui/index.html`

## Project structure

```
backend/    Spring Boot REST API
frontend/   React app
docker-compose.yml   MySQL + backend + frontend
```

## Running it

### With Docker (easiest)

```bash
cp .env.example .env      # change the passwords and JWT secret
docker compose up --build
```

- Frontend: http://localhost:3000
- Backend: http://localhost:9090
- Swagger: http://localhost:9090/swagger-ui/index.html

Two demo accounts are created on first start (change them in `.env`):

| Role  | Email          | Password |
|-------|----------------|----------|
| Admin | admin@demo.com | admin123 |
| User  | user@demo.com  | user123  |

The database starts empty, so log in as admin and add a few categories and products first.

### Without Docker

You need Java 17, Maven, Node 18 and a running MySQL.

```bash
# backend
cd backend
export MYSQL_PASSWORD=your-mysql-password
export JWT_SECRET=some-long-random-string-of-at-least-64-characters
mvn spring-boot:run

# frontend (in another terminal)
cd frontend
npm install
npm start
```

All backend settings are read from environment variables, with defaults for local development. See `backend/src/main/resources/application.properties` for the full list.

### Tests

```bash
cd backend
mvn test
```

Tests use an in-memory H2 database, so they don't need MySQL.

## What I changed after the course

- **Removed hard-coded secrets.** The JWT secret, database password and Google client ID were written directly in `application.properties`. They now come from environment variables, and `.env.example` documents them.
- **Removed real user data.** The course shipped a SQL dump with real names and emails. I deleted it and the app now creates two demo accounts on startup, only if they don't exist yet (before, it tried to insert them on every restart).
- **Docker setup.** Multi-stage Dockerfiles for the backend (Maven build, then a slim JRE image) and the frontend (React build served by Nginx), plus a `docker-compose.yml` that starts MySQL, waits for it to be healthy, then starts the API and the UI.
- **Configurable API URL.** The frontend had `http://localhost:9090` hard-coded. It now reads `REACT_APP_API_URL`, so the same code works locally and when deployed.
- **Tests without a database.** Added an H2 test configuration so `mvn test` runs anywhere, including CI.
- **CI.** A GitHub Actions workflow runs the backend tests and builds the frontend on every push.
- **Merged the two course repos into one** so the whole project can be cloned and run together.

## Credits

Based on the Electronic Store course project by [Learn Code With Durgesh](https://github.com/DurgeshCoder).
