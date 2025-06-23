# Use OpenJDK 17 base image
FROM openjdk:17-jdk-slim

# Tạo thư mục để chứa app
WORKDIR /app

# Copy file JAR vào container
COPY target/backend-0.0.1-SNAPSHOT.jar app.jar

# Expose port của app (trong Spring Boot mặc định là 8080)
EXPOSE 8080

# Lệnh chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]


