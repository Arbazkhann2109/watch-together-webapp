# Watch Together

A real-time watch-together web application for watching videos with friends while chatting and communicating through video calls.

The project is being built from scratch with a **Spring Boot backend**, **PostgreSQL**, **React**, **WebSocket/STOMP**, **JWT authentication**, and **WebRTC**.

> 🚧 **Status:** Active development

## Features

### Implemented

- User registration
- Secure password hashing with BCrypt
- User login
- JWT-based authentication
- Protected API endpoints
- Watch room creation
- Unique room codes
- Room membership
- PostgreSQL database
- Flyway database migrations

### Planned

- React frontend
- WebSocket/STOMP integration
- Real-time chat
- Synchronized video playback
- WebRTC video/audio calls
- Room participant management
- Security hardening
- Docker-based deployment

## Tech Stack

### Backend

- Java 17
- Spring Boot 4
- Spring Web
- Spring Data JPA
- Spring Security
- JWT
- BCrypt
- WebSocket / STOMP
- Flyway

### Database

- PostgreSQL 17

### Frontend

- React
- JavaScript / TypeScript

### Real-Time Communication

- WebSocket / STOMP — chat, room events, playback synchronization
- WebRTC — peer-to-peer audio/video communication

### Development & Deployment

- Maven
- Docker
- Docker Compose
- Git / GitHub

## Architecture

```text
                        ┌─────────────────────┐
                        │     React Client    │
                        │                     │
                        │  UI / Video / Chat  │
                        │  WebRTC              │
                        └──────────┬──────────┘
                                   │
                    HTTP / WebSocket / Signaling
                                   │
                                   ▼
                        ┌─────────────────────┐
                        │    Spring Boot      │
                        │      Backend        │
                        │                     │
                        │ Auth / Rooms / Chat │
                        │ Playback / Signaling│
                        └──────────┬──────────┘
                                   │
                                   ▼
                        ┌─────────────────────┐
                        │     PostgreSQL      │
                        │                     │
                        │ Users / Rooms /     │
                        │ Memberships / Data  │
                        └─────────────────────┘

                    WebRTC media
                 ┌──────────────────┐
                 │ Peer-to-peer     │
                 │ audio / video    │
                 └──────────────────┘
```

The Spring Boot server handles authentication, room management, chat coordination, playback synchronization, and WebRTC signaling.

Actual audio/video media will be handled by **WebRTC between clients**, rather than being streamed through the Spring Boot server.

## Project Structure

```text
watch-together/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── watchtogether/
│   │   │           ├── auth/
│   │   │           ├── config/
│   │   │           ├── room/
│   │   │           └── user/
│   │   │
│   │   └── resources/
│   │       ├── db/
│   │       │   └── migration/
│   │       └── application.properties
│   │
│   └── test/
│
├── docker-compose.yml
├── pom.xml
├── .gitignore
└── README.md
```

## Database

The application currently uses PostgreSQL with Flyway migrations.

Current database tables:

```text
users
  │
  │ 1:N
  ▼
watch_rooms
  │
  │ 1:N
  ▼
room_members
```

### Users

Stores registered application users.

### Watch Rooms

Stores rooms created by users.

Each room has a unique room code that can be shared with other users.

### Room Members

Connects users to watch rooms and prevents duplicate memberships.

## Authentication

Authentication uses:

```text
Register
   │
   ▼
BCrypt password hashing
   │
   ▼
PostgreSQL
```

For login:

```text
Username + Password
        │
        ▼
Spring Security
        │
        ▼
Password verification
        │
        ▼
JWT generated
        │
        ▼
Client
```

Protected requests use:

```http
Authorization: Bearer <JWT>
```

JWT secrets and database passwords are provided through environment variables and are **not committed to the repository**.

## Local Development

### Requirements

Install:

- Java 17
- Maven
- Docker Desktop
- Git

### Clone

```bash
git clone https://github.com/Arbazkhann2109/watch-together-webapp.git
cd watch-together-webapp
```

### Start PostgreSQL

Set the database password:

**Windows CMD**

```cmd
set DB_PASSWORD=postgres
```

Start PostgreSQL:

```bash
docker compose up -d
```

Verify the container:

```bash
docker ps
```

The PostgreSQL database runs on:

```text
localhost:5432
```

Database:

```text
watch_together
```

### Backend Environment Variables

The Spring Boot application expects:

```text
DB_PASSWORD
JWT_SECRET
```

Example for Windows CMD:

```cmd
set DB_PASSWORD=postgres
set JWT_SECRET=your-local-development-secret
```

> Never commit real production secrets to Git.

### Run the Backend

Using Maven:

```bash
mvn spring-boot:run
```

Or run the application from IntelliJ IDEA.

The backend runs on:

```text
http://localhost:8080
```

## Current API

### Authentication

#### Register

```http
POST /api/auth/register
```

Example:

```json
{
  "username": "testuser",
  "email": "test@example.com",
  "password": "password"
}
```

#### Login

```http
POST /api/auth/login
```

Example:

```json
{
  "username": "testuser",
  "password": "password"
}
```

Returns a JWT token.

### Watch Rooms

#### Create Room

```http
POST /api/rooms
Authorization: Bearer <JWT>
```

Example:

```json
{
  "name": "Friday Movie Night"
}
```

#### Join Room

```http
POST /api/rooms/join
Authorization: Bearer <JWT>
```

Example:

```json
{
  "roomCode": "ABC12345"
}
```

#### Get Room

```http
GET /api/rooms/{roomCode}
Authorization: Bearer <JWT>
```

> The API is actively evolving as development continues.

## Development Roadmap

- [x] Project setup
- [x] PostgreSQL setup
- [x] Flyway migrations
- [x] User entity
- [x] User registration
- [x] Password hashing
- [x] JWT authentication
- [x] Spring Security configuration
- [x] Watch room creation
- [x] Room membership
- [ ] Room retrieval endpoint
- [ ] React frontend
- [ ] WebSocket/STOMP
- [ ] Real-time chat
- [ ] Synchronized video playback
- [ ] WebRTC video calls
- [ ] Security hardening
- [ ] Docker production setup
- [ ] Deployment

## Security

The project follows these security principles:

- Passwords are never stored as plaintext.
- Passwords are hashed using BCrypt.
- JWT secrets are supplied through environment variables.
- Database credentials are supplied through environment variables.
- Secrets are excluded through `.gitignore`.
- Protected API endpoints require authentication.
- WebRTC media is intended to remain peer-to-peer.

## Project Goals

The goal of Watch Together is to provide a simple platform where friends can:

1. Create a private watch room.
2. Invite friends using a room code.
3. Watch videos together.
4. Keep playback synchronized.
5. Chat in real time.
6. Communicate through video/audio calls.

## License

This project is currently under development.

License information will be added when the project reaches its initial release.
