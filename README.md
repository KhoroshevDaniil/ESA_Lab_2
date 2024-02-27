# ESA_Lab_3

Приложение реализовано с использованием Spring Boot 3.2.2

Также была использована база данных PostgreSQL. Для корректной работы приложения переопределите подключение к БД в файле [application.properties](src/main/resources/application.properties)

В качестве предметной области была выбрана **научная конференция**, включающая в себя 2 объекта: участник (Participant) и научная статья (Paper)

# Updates
Добавлено:
* [xslt](src/main/resources/xslt)
* [DTO layer](src/main/java/com/example/esa_lab_2/dto)
* [REST controllers](src/main/java/com/example/esa_lab_2/controller/v2)

Изменено:
* [PaperController](src/main/java/com/example/esa_lab_2/controller/v2/PaperRestController.java) 
и 
[ParticipantController](src/main/java/com/example/esa_lab_2/controller/v2/ParticipantRestController.java) со 2 лабы переехали в модуль [controller.v1](src/main/java/com/example/esa_lab_2/controller/v1)
* В [index.html](src/main/resources/templates/index.html) добавлены ссылки с новым API

# API
## Participant's API
### Get all participants
`GET /api/v2/participants`
### Response body example
```json
[
  {
    "id": 2,
    "name": "Daniil",
    "age": 22,
    "academicDegree": "Bachelor of Science"
  },
  {
    "id": 4,
    "name": "Dmitry Pukich",
    "age": 0,
    "academicDegree": "Bachelor of Science"
  }
]
```

### Get participant by id
`GET /api/v2/participants/{id}`
### Response body example
```json
{
  "id": 2,
  "name": "Daniil",
  "age": 22,
  "academicDegree": "Bachelor of Science"
}
```

### Create participant
`POST /api/v2/participants/create`
### Request body example
```json
{
  "name": "Daniil Shustanov",
  "age": 12,
  "academicDegree": "Master of Science"
}
```
### Response example

    HTTP/1.1 200 OK
    Date: Tue, 27 Feb 2024 14:42:18 GMT
    Status: 200 OK
    Connection: keep-alive
    Keep-Alive: timeout=60
    Content-Length: 0

### Delete participant
`POST /api/v2/participants/delete/{id}`
### Response example

    HTTP/1.1 200 OK
    Date: Tue, 27 Feb 2024 14:45:06 GMT
    Status: 200 OK
    Connection: keep-alive
    Keep-Alive: timeout=60
    Content-Length: 0

## Paper's API
### Get all papers
`GET /api/v2/papers`
### Response body example
```json
[
  {
    "id": 1,
    "title": "sdfsdf",
    "year": 11111,
    "authorName": "Daniil",
    "authorId": 2
  },
  {
    "id": 2,
    "title": "asdasd",
    "year": 2225,
    "authorName": "Daniil",
    "authorId": 2
  },
  {
    "id": 3,
    "title": "some title",
    "year": 2023,
    "authorName": "Dmitry Pukich",
    "authorId": 4
  }
]
```

### Get paper by id
`GET /api/v2/papers/{id}`
### Response body example
```json
{
  "id": 3,
  "title": "some title",
  "year": 2023,
  "authorName": "Dmitry Pukich",
  "authorId": 4
}
```

### Create participant
`POST /api/v2/papers/create`
### Request body example
```json
{
  "title": "Some Title",
  "year": 2024,
  "author": "Daniil"
}
```
### Response example

    HTTP/1.1 200 OK
    Date: Tue, 27 Feb 2024 14:50:04 GMT
    Status: 200 OK
    Connection: keep-alive
    Keep-Alive: timeout=60
    Content-Length: 0

### Delete participant
`POST /api/v2/papers/delete/{id}`
### Response example

    HTTP/1.1 200 OK
    Date: Tue, 27 Feb 2024 14:51:33 GMT
    Status: 200 OK
    Connection: keep-alive
    Keep-Alive: timeout=60
    Content-Length: 0