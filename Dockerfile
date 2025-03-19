# Use an official image with Docker installed
FROM docker:latest

# Install Docker Compose
RUN apk add --no-cache docker-compose

# Set working directory
WORKDIR /app

# Copy the docker-compose file into the container
COPY docker-compose.yml .

# Run the Selenium container when this image is used
CMD ["docker-compose", "up", "-d"]
