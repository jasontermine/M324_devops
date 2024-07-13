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

## Dockerfile für MYSQL

- In diesem Dockerfile wird ein MySQL-Image erstellt, das eine Datenbank und Benutzer mit den angegebenen Umgebungsvariablen initialisiert. 

```Dockerfile
FROM mysql:latest

ENV MYSQL_ROOT_PASSWORD=admin
ENV MYSQL_USER=tester
ENV MYSQL_PASSWORD=testing
ENV MYSQL_DATABASE=todo

COPY ./init.sql /docker-entrypoint-initdb.d/
```

- In diesem `init.sql` Skript wird die Datenbank, die Tabelle und der Benutzer initialisiert.

```sql
USE todo;

CREATE TABLE IF NOT EXISTS todo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    task VARCHAR(255) NOT NULL,
    done BOOLEAN NOT NULL DEFAULT FALSE
);

GRANT ALL PRIVILEGES ON todo.* TO 'tester'@'%';
FLUSH PRIVILEGES;
```

- `ENV`: Definiert die Umgebungsvariablen für den MySQL-Container.
- `COPY ./init.sql /docker-entrypoint-initdb.d/`: Kopiert die `init.sql` Datei in das Verzeichnis `/docker-entrypoint-initdb.d/`, damit sie beim Starten des Containers ausgeführt wird.
- `init.sql`: Initialisiert die Datenbank, den Benutzer und das Passwort.

## Docker Compose für das Build des Frontend, Backends und der Datenbank
- In diesem `compose.yml` File werden die Services für das Frontend, Backend und die Datenbank definiert.

```yml
version: '3.8'

services:
  database:
    build: ./database
    image: todo-database
    ports:
      - "3306:3306"
    restart: always
    volumes:
      - database:/var/lib/mysql

  frontend:
    build: ./frontend
    image: todo-frontend
    ports:
      - "80:80"
    restart: always

  backend:
    build: ./backend
    image: todo-backend
    ports:
      - "8080:8080"
    restart: always

volumes:
  database:
```

- `services`: Definiert die Services für die Datenbank, das Frontend und das Backend.
- `build`: Gibt den Pfad zum Dockerfile an.
- `image`: Gibt den Namen des Images an.
- `ports`: Definiert die Ports, die für die Services freigegeben werden.
- `restart`: Definiert das Neustartverhalten der Services.
- `volumes`: Definiert die Volumes für die Datenbank.

## Docker Compose welches die Images eigenen Images auf Docker Hub pullt

- In diesem `compose.yml` File werden die Services für das Frontend, Backend und die Datenbank definiert, die die eigenen Images von Docker Hub verwenden.

```yml
name: todoapplication

services:
  backend:
    image: christophknuchelwiss/todo-backend:latest
    ports:
      - "8080:8080"
    restart: always

  frontend:
    image: christophknuchelwiss/todo-frontend:latest
    ports:
      - "80:80"
    restart: always
    depends_on:
      - backend

  database:
    image: christophknuchelwiss/todo-database:latest
    ports:
      - "3306:3306"
    restart: always
    volumes:
      - database:/var/lib/mysql

volumes:
  database:
```

- `image`: Gibt den Namen des Images auf Docker Hub an.
- `depends_on`: Definiert die Abhängigkeiten zwischen den Services.
-  `volumes`: Definiert die Volumes für die Datenbank.

## GitHub Actions Workflow

- In diesem GitHub Actions Workflow wird der Build- und Push-Prozess für die Docker-Images automatisiert.

```yml
name: Publish Docker image to Docker Hub

on:
  release:
    types: [published]

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
      - name: Checkout repository
        uses: actions/checkout@v3

      - name: Login to Docker Hub
        uses: docker/login-action@v3
        with:
          username: ${{ secrets.DOCKERHUB_USERNAME }}
          password: ${{ secrets.DOCKERHUB_TOKEN }}

      - name: Set up Docker Buildx
        uses: docker/setup-buildx-action@v3

      - name: Build and Tag Images with Compose
        run: |
          cd Sidequests/SQ8
          docker-compose build
          docker tag todo-backend ${{ secrets.DOCKERHUB_USERNAME }}/todo-backend:latest
          docker tag todo-frontend ${{ secrets.DOCKERHUB_USERNAME }}/todo-frontend:latest
          docker tag todo-database ${{ secrets.DOCKERHUB_USERNAME }}/todo-database:latest

      - name: Push Backend Image
        run: |
          docker push ${{ secrets.DOCKERHUB_USERNAME }}/todo-backend:latest

      - name: Push Frontend Image
        run: |
          docker push ${{ secrets.DOCKERHUB_USERNAME }}/todo-frontend:latest

      - name: Push Database Image
        run: |
          docker push ${{ secrets.DOCKERHUB_USERNAME }}/todo-database:latest
```

- `on`: Definiert die Auslöser für den Workflow.
- `jobs`: Definiert die Jobs für den Workflow.
- `uses`: Verwendet die Docker-Login-Action, um sich bei Docker Hub anzumelden.
- `with`: Definiert die Benutzername und das Token für Docker Hub.
- `docker/setup-buildx-action@v3`: Setzt Docker Buildx für den Build-Prozess.
- `docker-compose build`: Baut die Docker-Images mit Docker Compose.
- `docker tag`: Taggt die Docker-Images mit dem Docker Hub Benutzernamen.
- `docker push`: Pushed die Docker-Images auf Docker Hub.

- `secret.DOCKERHUB_USERNAME` und `secret.DOCKERHUB_TOKEN`: Definiert die Docker Hub Zugangsdaten als Secrets im GitHub Repository.

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