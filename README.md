# open-library-api-tests
![This is an image](/icons/Java.png)![This is an image](/icons/Gradle.png)![This is an image](/icons/Rest-Assured.png)![This is an image](/icons/Intelij_IDEA.png)![This is an image](/icons/JUnit5.png)![This is an image](/icons/Allure_Report.png)![This is an image](/icons/AllureTestOps.png)
## Описание проекта
Учебный проект реализации автотестирования **Rest Api**.<br/>
>В качестве объекта тестирование выбран сайт https://openlibrary.org/ с открытым api.<br/>

![This is an image](/images/open-library.png)

Сайт позволяет получать информацию о книгах по автору, названию, теме, году издания, издателю, языках публикаций и прочем.

## Список проверок, реализованных в автотестах
Поиск на сайте может выполняться по двум видам запросов: обычном и solr-query. Поэтому для тесткейсов настроены две **Feautures: Query, Solr query**.
- Solr query. Language. Поиск книг автора на русском языке
- Solr query. Language. Поиск книг автора на английском языке
- Solr query. Person. Поиск книг о заданной персоне
- Solr query. Place. Поиск книг о заданном месте
- Solr query. Publisher. Поиск книг по издателю
- Solr query. Subject. Поиск книг на заданную тему
- Обычный поиск. Полное наименование книги и имя-фамилия автора
- Поиск по полному названию книги. Solr query
- Поиск по полному названию книги. Обычный поиск
- Поиск по фамилии автора. Solr query
- Поиск по фамилии автора. Обычный поиск
- Поиск по фамилии и имени автора. Solr query
- Поиск по фамилии и имени автора. Обычный поиск
- Поиск книги по фамилии и имени автора. Обычный поиск
- Поиск по части названия книги. Обычный поиск

#### Пример запуска из командной строки
```bash
gradle clean test
```
## Запуск автотестов выполняется на сервере Jenkins
> <a target="_blank" href="https://jenkins.autotests.cloud/job/09-ElenaSeversk-open-library-api-tests/">Ссылка на проект в Jenkins</a>

![This is an image](/images/jenkins.png)

Для запуска тестов выбрать пункт "Собрать сейчас"

