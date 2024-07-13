# Dockerfile.frontend

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
