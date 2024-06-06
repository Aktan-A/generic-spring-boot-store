# Generic Spring Boot Store

## Project Setup
```bash
git clone git@github.com:Aktan-A/neobis-week3.git
./mvnw clean spring-boot:run
```

## Running Tests
```bash
./mvnw test
```

## Running Docker-Compose
```bash
docker compose -f docker-compose.dev.yml up -d --build
```

## Technologies
- Spring
- Spring Boot
- Spring MVC
- MySQL
- Lombok
- JWT
- Docker

## Environment Variables
| Key         | Description               |
|-------------|---------------------------|
| DB_URL      | Database url.             |
| DB_USERNAME | Database user username.   |
| DB_PASSWORD | Database user password.   |
| SECRET_KEY  | Secret for access tokens. |