# CPU Manager

A compact full‑stack sample project for managing desktop CPUs.

## Features

* List all CPUs (brand, model, socket)
* Create, edit and delete a CPU with full specs  
  * brand, model, socket, clock speed, cores, threads, TDP, price €  
* Separate, read‑only socket reference table

## Technology

| Layer      | Stack                                  |
|------------|----------------------------------------|
| Backend    | Spring Boot 3 · Java 21 · Maven        |
| Database   | MariaDB / MySQL                        |
| Frontend   | React 19 (Vite) · Material UI v5       |

## Quick start (development)

```bash
# database (MariaDB or MySQL)
CREATE DATABASE pcstore;
CREATE USER 'app'@'%' IDENTIFIED BY 'app';
GRANT ALL ON pcstore.* TO 'app';

# backend
cd CPUapp
./mvnw spring-boot:run          # -> http://localhost:8081

# frontend
cd cpu-ui
npm install
npm run dev                     # -> http://localhost:5173
```

The React dev server proxies requests under `/api` to port **8081**, so CORS is handled automatically during development.

## Build & run (production)

```bash
# build static frontend
cd cpu-ui
npm run build                   # files in dist/

# copy static files into Spring resources
cd ..
copy cpu-ui\dist\* CPUapp\src\main\resources\static\ /Y

# build single executable JAR
cd CPUapp
./mvnw clean package -Pprod
java -jar target/CPUapp-*.jar   # back + front on :8081
```

## REST API

| Method | Endpoint            | Description        |
|--------|---------------------|--------------------|
| GET    | `/api/cpus`         | list CPUs          |
| GET    | `/api/cpus/{id}`    | get one CPU        |
| POST   | `/api/cpus`         | create CPU         |
| PUT    | `/api/cpus/{id}`    | update CPU         |
| DELETE | `/api/cpus/{id}`    | delete CPU         |
| GET    | `/api/sockets`      | list sockets       |

## Project layout

```
CPUapp/
 └─ src/main/java/com/example/cpuapp
     ├─ controller/   REST endpoints
     ├─ model/        JPA entities
     ├─ service/      business logic
     └─ dto/          record objects
cpu-ui/
 └─ src/components/   React pages & widgets
```

## License

MIT — free for personal and commercial use.
