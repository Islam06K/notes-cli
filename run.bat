@echo off
docker run --rm -v "%cd%\/app/data" kostoev/notes-cli:v1.1.0 %*