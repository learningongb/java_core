# Домашняя работа 1

Проект содержит приложение для сохранения заметок в текстовый файл.  
По каждой заметке вводится заголовок и текст заметки. В файл автоматически дописывается текущая дата и время.

## Компиляция из командной строки

```bash
javac -sourcepath ./src/ -d out src/ru/gb/notes/Main.java
```

## Запуск скомпилированного кода

```bash
java -classpath ./out ru.gb.notes.Main notes.txt
```
Заметки сохранятся в файл notes.txt 

## Использование утилиты make

### Компиляция
```bash
make
```

### Создание документации
```bash
make doc
```

### Удаление скомпилированных файлов и документации
```bash
make clean
```

## Запуск в Docker

### Компиляция
#### Создание образа
``` bash
sudo docker build -t java_compile docker_make/
```
#### Запуск контейнера для компиляции
```bash
sudo docker run -it -v ./src:/java/src -v ./out:/java/out java_compile
```

### Выполнение
#### Создание образа
``` bash
sudo docker build -t java_compile docker_run/
```
#### Запуск контейнера для компиляции
```bash
sudo docker run -it -v ./notes.txt:/java/notes.txt -v ./out:/java/out java_run
```
