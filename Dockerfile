FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY src/ ./src/
COPY data/ ./data/

RUN mkdir -p data && \
    javac -d . src/com/example/*.java

VOLUME ["/app/data"]

ENTRYPOINT ["java", "-cp", ".", "com.example.App"]