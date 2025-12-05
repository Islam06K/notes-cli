## 🚀 Запуск (без Java, без сборки, без GitHub)

Если у вас установлен [Docker](https://www.docker.com/products/docker-desktop/), выполните **одну команду**:

```bash
docker run --rm -v "$(pwd)//app/data" kostoev/notes-cli:v1.1.0 --cmd=add --text="Привет из облака!"