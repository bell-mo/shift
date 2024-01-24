## Описание
Приложение представляет собой выполненное тестовое задание для ШИФТ Лаб.

[ШИФТ лаб](https://team.cft.ru/start/lab)
## Используемые зависимости

[Gradle 8.5](https://docs.gradle.org/current/userguide)
[Spring Boot 3.2.2](https://spring.io/projects/spring-boot)
[H2 Database](https://h2database.github.io/html/main.html)
[Hibernate ORM](https://hibernate.org/orm/)

## Функционал
На вход в теле POST-запроса поступает массив интервалов из чисел, либо символов. Находятся пересечения интервалов, пересекающиеся интервалы объединяются в один и сохраняются в in-memory базе данных. 
Далее мы можем запросить минимальный из сохраненных интервал, после чего получить его в теле ответа на соответствующий GET-запрос.
## Интерфейс
### POST-запрос:
http://localhost:8080/api/v1/intervals/merge?kind=digits - URL POST-запроса для чисел

Пример тела запроса:
```
[
  [1, 4],
  [3, 6],
  [8, 10],
  [9, 11]
]
```
http://localhost:8080/api/v1/intervals/merge?kind=letters - URL POST-запроса для символов

Пример тела запроса:
```
[
  ["a", "f"],
  ["d", "j"],
  ["r", "z"]
]
```

### GET-запрос:
http://localhost:8080/api/v1/intervals/min?kind=digits - URL GET-запроса для чисел

Пример тела ответа:
```
[
  1,
  6
]
```

http://localhost:8080/api/v1/intervals/min?kind=letters - URL GET-запроса для символов

Пример тела ответа:
```
[
  "a",
  "j"
]
```
## Сборка

Для сборки приложения в корневом каталоге введите

```./gradlew build ``` Для Linux

``` gradlew build``` Для Windows
## Запуск

Для запуска приложения (ТОЛЬКО после сборки) в корневом каталоге введите

```./gradlew run``` Для Linux

```gradlew run``` Для Windows

## Настойка и конфигурирование

Необходимые настройки приложения задаются в файле ```settings.gradle``` в корневом каталоге.

Для изменения свойств приложения используйте файлы ```build.gradle``` в корневом каталоге и
```src/main/resources/application.properties```
