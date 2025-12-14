# сборка образа
docker build -t notes-cli:dev .

# добавить заметку
docker run --rm -v "${PWD}\data:/app/data" notes-cli:dev --cmd=add --text="First note"

# список заметок
docker run --rm -v "${PWD}\data:/app/data" notes-cli:dev --cmd=list

# количество заметок
docker run --rm -v "${PWD}\data:/app/data" notes-cli:dev --cmd=count
