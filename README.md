# ESA_Lab_2

Приложение реализовано с использованием Spring Boot 3.2.2

Также была использована база данных PostgreSQL. Для корректной работы приложения переопределите подключение к БД в файле [application.properties](src/main/resources/application.properties)

В качестве предметной области была выбрана **научная конференция**, включающая в себя 2 объекта: участник (Participant) и научная статья (Paper)

## Data Layer ##
В модуле [com.example.esa_lab_2.model](src/main/java/com/example/esa_lab_2/model) реализовано два Java Beans: [Participant](src/main/java/com/example/esa_lab_2/model/Participant.java) и [Paper](src/main/java/com/example/esa_lab_2/model/Paper.java).

Data Layer полностью повторяется из предыдущей лабы, подробнее о реализованных JavaBean [тут](https://github.com/KhoroshevDaniil/ESA_Lab_1?tab=readme-ov-file#data-layer)

## Business Layer ##
За бизнес-логику приложения отвечают следующие сервисы:
- [PaperService](src/main/java/com/example/esa_lab_2/service/PaperService.java)
- [ParticipantService](src/main/java/com/example/esa_lab_2/service/ParticipantService.java)

## View Layer ##
За обработку запросов отвечают следующие контроллеры:
- [IndexController](src/main/java/com/example/esa_lab_2/controller/IndexController.java) - обрабатывает действия на странице index.html
- [PaperController](src/main/java/com/example/esa_lab_2/controller/PaperController.java) - обрабатывает действия на странице papers.html
- [ParticipantController](src/main/java/com/example/esa_lab_2/controller/ParticipantController.java) - обрабатывает действия на странице participants.html

UI реализован в следующих html страницах:
- [index.html](src/main/resources/templates/index.html)
- [papers.html](src/main/resources/templates/papers.html)
- [participants.html](src/main/resources/templates/participants.html)

### Стартовая страница приложения: ###

![](images/index.jpg)

### Страница для создания, удаления и отображения участников конференции: ###

![](images/participants.jpg)

### Страница для создания, удаления и отображения статей: ###

![](images/papers.jpg)