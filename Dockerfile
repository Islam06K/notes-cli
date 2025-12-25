FROM eclipse-temurin:17-jdk


WORKDIR /app

# Копируем исходники
COPY src ./src

# Создаём папку для данных (если её нет)
RUN mkdir -p /app/data

# Компилируем при сборке образа (лучше, чем при запуске)
RUN javac -d bin src/com/example/*.java

# Запускаем приложение. "$@" — это аргументы, переданные в docker run
ENTRYPOINT ["java", "-cp", "bin", "com.example.App"]

# По умолчанию — без аргументов (можно удалить, если не нужно)
CMD []
