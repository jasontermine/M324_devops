_Christopher Knuchel, Jason Termine_

# Inhaltsverzeichnis

## Dokumentation für Docker-Setup und GitHub Actions Workflow

Diese Dokumentation beschreibt die Schritte zur Erstellung und Verwaltung von Docker-Containern für das Frontend und Backend sowie die Automatisierung des Build- und Push-Prozesses mit GitHub Actions.

## Dockerfile für das Frontend
1. Im frontend-Verzeichnis ein neues Dockerfile erstellt mit folgendem Inhalt:
```Dockerfile
# Build stage
FROM node:latest AS build

# Set working directory
WORKDIR /app

# Install dependencies
COPY package*.json ./
RUN npm install

# Copy source files
COPY . .

# Build the app
RUN npm run build

# Production stage
FROM nginx:alpine

# Copy built files from the build stage
COPY --from=build /app/dist /usr/share/nginx/html

# Expose port
EXPOSE 80

# Command to run the app (Nginx will automatically start)
CMD ["nginx", "-g", "daemon off;"]
```

2. **Build-Phase**:
- `FROM node:latest AS build`: Verwendet das offizielle Node-Image als Basisimage für den Build-Prozess.
- `WORKDIR /app`: Setzt das Arbeitsverzeichnis auf /app.
- `COPY package*.json ./`: Kopiert package.json und package-lock.json in das Arbeitsverzeichnis.
- `RUN npm install`: Installiert die Abhängigkeiten.
- `COPY . .`: Kopiert alle Dateien in das Arbeitsverzeichnis.
- `RUN npm run build`: Baut die Anwendung.

3. **Produktionsphase**:
- `FROM nginx:alpine`: Verwendet das offizielle Nginx-Image als Basisimage für die Produktionsphase.
- `COPY --from=build /app/dist /usr/share/nginx/html`: Kopiert die gebauten Dateien aus dem Build-Image in das Nginx-Image.
- `EXPOSE 80`: Öffnet den Port 80.
- `CMD ["nginx", "-g", "daemon off;"]`: Startet Nginx im Vordergrund.

## Dockerfile für das Backend

Dieses Dockerfile erstellt ein Produktions-Image für eine Maven-basierte Java-Anwendung und verwendet ein Multi-Stage-Build, um das endgültige Image schlank zu halten.

```Dockerfile
# Stage 1: Build the JAR file
FROM maven:3.8.4-openjdk-17 AS builder

# Set the working directory for the build
WORKDIR /app

# Copy the pom.xml and source code
COPY pom.xml .
COPY src ./src

# Package the application
RUN mvn clean package

# Stage 2: Create the final image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the builder stage
COPY --from=builder /app/target/todo-0.0.1-SNAPSHOT.jar /app/todo.jar

# Define the command to run the JAR file with arguments
CMD ["java", "-jar", "todo.jar"]
```

In diesem Dockerfile werden zwei Stages verwendet:

1. **Build-Stage**: 
    - Verwendet das Maven-Image, um das JAR-Archiv zu erstellen.

2. **Produktions-Stage**: 
    - Verwendet das OpenJDK-Image, um das endgültige Produktions-Image zu erstellen. Kopiert das JAR-Archiv aus der Build-Stage in das Produktions-Image und definiert den Befehl zum Ausführen des JAR-Archivs.

## Docker Compose

## GitHub Actions Workflow

## Schritte zum lokalen Testen
1. **Vorausssetzungen**:
    - Docker und Docker Compose müssen auf dem lokalen Rechner installiert sein.

2. **Compose-Datei ausführen**:
    - Um die Docker compose Datei auszuführen, navigiere zum `SQ8` Verzeichnis, in dem die `compose.yml` Datei liegt und führe den folgenden Befehl aus:
```bash
docker compose up -d
```

Dieser Befehl startet die Frontend-Anwendung auf Port 80 und die Backend-Anwendung auf Port 5000.

# Hinweis

- **Github Secrets**: 
    - Stelle sicher, dass die Docker Hub Zugangsdaten (DOCKER_USERNAME und DOCKER_TOKEN) als Secrets im GitHub Repository konfiguriert sind.
- **Pfadangaben**: 
    - Überprüfe, ob die Pfade in der GitHub Actions Workflow Datei korrekt sind und auf die richtigen Dateien verweisen.

- **Docker Compose Push**: 
    - Der `compose push` Befehl verwendet die `image ` Tags, die in der `compose.yml` Datei angegeben sind. Stelle sicher, dass diese Tags korrekt konfiguriert sind.