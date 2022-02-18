import io.qameta.allure.AllureId;
import io.qameta.allure.Owner;
import model.FullResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

public class RestApiTests {
    private final String favoriteBook = "Falshivye zerkala";
    private final String errorMessage = "Нет информации по запросу в библиотеке";
    FullResponse data;

    @Test
    @AllureId("7357")
    @DisplayName("Solr query. Language. Поиск книг у автора на русском языке")
    @Owner("allure8")
    void solrQueryLanguageRu() {
        step("Ищем информацию об авторе по запросу q=author:lukyanenko&language:rus", () ->
            data = SearchUtils.getSearchResult("q=author:lukyanenko&language:rus"));

        step("Проверяем, что результатов поиска > 0", () ->
                Assertions.assertTrue(data.getDocs().size() > 0, errorMessage));

        step("Проверяем, что в списке результатов есть книги на русском языке", () ->
                Assertions.assertTrue(data.getDocs().stream().anyMatch(libraryDoc ->
                        libraryDoc.getLanguage().stream().anyMatch(s -> s.equals("rus"))),
                "Ошибка. В выборке нет книг на русском языке"));

    }

    @Test
    @DisplayName("Solr query. Language. Поиск книг у автора на английском языке")
    @Owner("allure8")
    void solrQueryLanguageEn() {
        step("Ищем информацию об авторе по запросу q=author:lukyanenko&language:en", () ->
            data = SearchUtils.getSearchResult("q=author:lukyanenko&language:en"));

        step("Проверяем, что результатов поиска > 0", () ->
                Assertions.assertTrue(data.getDocs().size() > 0, errorMessage));

        step("Проверяем, что в списке результатов есть книги на английском языке", () ->
                Assertions.assertTrue(data.getDocs().stream().anyMatch(libraryDoc ->
                        libraryDoc.getLanguage().stream().anyMatch(s -> s.contains("en"))),
                "Ошибка. В выборке нет книг на английском языке"));
    }

    @Test
    @AllureId("7356")
    @DisplayName("Solr query. Person. Поиск книг о заданной персоне")
    @Owner("allure8")
    public void searchBookAboutPerson() {
        step("Ищем информацию об Одри Херберн по запросу q=person: Audrey+Hepburn", () ->
            data = SearchUtils.getSearchResult("q=person: Audrey+Hepburn"));

        step("Проверяем, что результатов поиска > 0", () ->
                Assertions.assertTrue(data.getDocs().size() > 0, errorMessage));

        step("Проверяем, что в списке результатов есть книги об Одри Хепберн", () ->
            Assertions.assertTrue(data.getDocs().stream().anyMatch(libraryDoc ->
                            libraryDoc.getTitle().contains("Hepburn")),
                    "Ошибка. В выборке нет книг об Одри Хепберн"));
    }

    @Test
    @AllureId("7351")
    @DisplayName("Поиск по части названия книги. Обычный поиск")
    @Owner("allure8")
    public void test2() {
        step("Ищем информацию о нашей люимой книге по запросу title=zerkala", () ->
            data = SearchUtils.getSearchResult("title=zerkala"));

        step("Проверяем, что результатов поиска > 0", () ->
                Assertions.assertTrue(data.getDocs().size() > 0, errorMessage));

        step("Проверяем, что в списке результатов есть наша любимая книга", () ->
            Assertions.assertTrue(data.getDocs().stream().anyMatch(libraryDoc ->
                            libraryDoc.getTitle().equals(favoriteBook)),
                    "Ошибка. В выборке нет нашей любимой книги"));
    }

    @Test
    @AllureId("7354")
    @DisplayName("Solr query. Place. Поиск книг о заданном месте")
    @Owner("allure8")
    public void searchBooksAboutPlace() {
        step("Ищем книги о Риме по запросу q=place:Rome", () ->
            data = SearchUtils.getSearchResult("q=place:Rome"));

        step("Проверяем, что результатов поиска > 0", () ->
            Assertions.assertTrue(data.getDocs().size() > 0, errorMessage));

        step("Проверяем, что в списке результатов есть книги о Риме", () ->
            Assertions.assertTrue(data.getDocs().stream().anyMatch(libraryDoc ->
                            libraryDoc.getTitle().contains("Rome")),
                    "Ошибка. В выборке нет книг о Риме"));
    }

    @Test
    @AllureId("7358")
    @DisplayName("Solr query. Publisher. Поиск книг по издателю")
    @Owner("allure8")
    public void searchBookOfPublisher() {
        step("Ищем книги издательства 'Neva' по запросу q=publisher:Neva", () ->
            data = SearchUtils.getSearchResult("q=publisher:Neva"));

        step("Проверяем, что результатов поиска > 0", () ->
            Assertions.assertTrue(data.getDocs().size() > 0, errorMessage));

        step("Проверяем, что в списке результатов есть книги издательства 'Neva'", () ->
                Assertions.assertTrue(data.getDocs().stream().anyMatch(libraryDoc ->
                            libraryDoc.getPublisher().stream().anyMatch(s -> s.equals("Neva"))),
                    "Ошибка. В выборке нет книг этого издательства"));
    }

    @Test
    @AllureId("7355")
    @DisplayName("Solr query. Subject. Поиск книг на заданную тему")
    @Owner("allure8")
    public void searchBooksAboutSubject() {
        step("Ищем книги о танго по запросу q=subject:tango", () ->
            data = SearchUtils.getSearchResult("q=subject:tango"));

        step("Проверяем, что результатов поиска > 0", () ->
            Assertions.assertTrue(data.getDocs().size() > 0, errorMessage));

        step("Проверяем, что в списке результатов есть книги о танго", () ->
            Assertions.assertTrue(data.getDocs().stream().anyMatch(libraryDoc ->
                            libraryDoc.getTitle().contains(" tango ")),
                    "Ошибка. В выборке нет книг о танго"));
    }

    @Test
    @AllureId("7353")
    @DisplayName("Обычный поиск. Полное наименование книги и имя-фамилия автора")
    @Owner("allure8")
    public void searchBookForAuthorFullNameAndFullTitle() {
        step("Ищем книги о танго по запросу author=Sergei+Lukyanenko&title=Falshivye+zerkala", () ->
            data = SearchUtils.getSearchResult("author=Sergei+Lukyanenko&title=Falshivye+zerkala"));

        step("Проверяем, что результатов поиска = 1", () ->
            Assertions.assertEquals(1,data.getDocs().size(), errorMessage));

        step("Проверяем, что в списке результатов есть наша любимая книга", () ->
            Assertions.assertTrue(data.getDocs().stream().allMatch(libraryDoc ->
                            libraryDoc.getTitle().equals(favoriteBook)),
                    "Ошибка. Книга не найдена!"));
    }

    @Test
    @AllureId("7349")
    @DisplayName("Поиск по полному названию книги. Solr query")
    @Owner("allure8")
    public void solrQueryOnFullBookName() {
        step("Ищем книги о танго по запросу q=title:Falshivye+zerkala", () ->
            data = SearchUtils.getSearchResult("q=title:Falshivye+zerkala"));

        step("Проверяем, что результатов поиска > 0", () ->
            Assertions.assertTrue(data.getDocs().size() > 0, errorMessage));

        step("Проверяем, что в списке результатов есть наша любимая книга", () ->
            Assertions.assertTrue(data.getDocs().stream().anyMatch(libraryDoc ->
                            libraryDoc.getTitle().equals(favoriteBook)),
                    "Ошибка. Книга не найдена!"));
    }

    @Test
    @AllureId("7348")
    @DisplayName("Поиск по полному названию книги. Обычный поиск")
    @Owner("allure8")
    public void searchOnFullBookName() {
        step("Ищем книги о танго по запросу title=Falshivye zerkala", () ->
            data = SearchUtils.getSearchResult("title=Falshivye zerkala"));

        step("Проверяем, что результатов поиска > 0", () ->
            Assertions.assertTrue(data.getDocs().size() > 0, errorMessage));

        step("Проверяем, что в списке результатов есть наша любимая книга", () ->
            Assertions.assertTrue(data.getDocs().stream().anyMatch(libraryDoc ->
                            libraryDoc.getTitle().equals(favoriteBook)),
                    "Ошибка. Книга не найдена!"));
    }

    @Test
    @AllureId("7345")
    @DisplayName("Поиск по фамилии автора. Solr query")
    @Owner("allure8")
    public void searchSolrQueryOnAuthorLastName() {
        step("Ищем информацию об авторе по запросу q=author:lukyanenko", () ->
            data = SearchUtils.getSearchResult("q=author:lukyanenko"));

        step("Проверяем, что результатов поиска > 0", () ->
            Assertions.assertTrue(data.getDocs().size() > 0, errorMessage));

        step("Проверяем, что в списке результатов все авторы с фамилией Lukyanenko", () ->
            Assertions.assertTrue(data.getDocs().stream().allMatch(libraryDoc ->
                    libraryDoc.getAuthor_name().stream().allMatch(s -> s.contains("Lukyanenko"))),
                    "Ошибка. В выборке есть авторы с другой фамилией"));
    }

    @Test
    @AllureId("7344")
    @DisplayName("Поиск по фамилии автора. Обычный поиск")
    @Owner("allure8")
    public void searchOnAuthorLastName() {
        step("Ищем информацию об авторе по запросу author=Lukyanenko", () ->
            data = SearchUtils.getSearchResult("author=Lukyanenko"));

        step("Проверяем, что результатов поиска > 0", () ->
            Assertions.assertTrue(data.getDocs().size() > 0, errorMessage));

        step("Проверяем, что в списке результатов есть авторы с фамилией Lukyanenko", () ->
            Assertions.assertTrue(data.getDocs().stream().anyMatch(libraryDoc ->
                            libraryDoc.getAuthor_name().stream().anyMatch(s -> s.contains("Lukyanenko"))),
                    "Ошибка. В выборке есть документы авторов с другой фамилией"));
    }

    @Test
    @AllureId("7347")
    @DisplayName("Поиск по фамилии и имени автора. Solr query")
    @Owner("allure8")
    public void test12() {
        step("Ищем информацию об авторе по запросу q=Sergei+Lukyanenko", () ->
            data = SearchUtils.getSearchResult("q=Sergei+Lukyanenko"));

        step("Проверяем, что результатов поиска > 0", () ->
            Assertions.assertTrue(data.getDocs().size() > 0, errorMessage));

        step("Проверяем, что в списке результатов все авторы с ФИО Sergei Lukyanenko", () ->
            Assertions.assertTrue(data.getDocs().stream().anyMatch(libraryDoc ->
                            libraryDoc.getAuthor_name().stream().anyMatch(s -> s.equals("Sergei Lukyanenko"))),
                    "Ошибка. В выборке есть авторы с другой фамилией"));
    }

    @Test
    @AllureId("7346")
    @DisplayName("Поиск по фамилии и имени автора. Обычный поиск")
    @Owner("allure8")
    public void searchOnAuthorFirstAndLastName() {
        step("Ищем информацию об авторе по запросу author=Sergei Lukyanenko", () ->
            data = SearchUtils.getSearchResult("author=Sergei Lukyanenko"));

        step("Проверяем, что результатов поиска > 0", () ->
            Assertions.assertTrue(data.getDocs().size() > 0, errorMessage));

        step("Проверяем, что в списке результатов все авторы с ФИО Sergei Lukyanenko", () ->
            Assertions.assertTrue(data.getDocs().stream().anyMatch(libraryDoc ->
                            libraryDoc.getAuthor_name().stream().anyMatch(s -> s.equals("Sergei Lukyanenko"))),
                    "Ошибка. В выборке есть авторы с другой фамилией"));
    }

    @Test
    @DisplayName("Поиск книги у автора по фамилии и имени автора. Обычный поиск")
    @Owner("allure8")
    public void searchBookInResulListOnAuthorFirstAndLastName() {
        step("Ищем информацию об авторе по запросу author=Sergei Lukyanenko", () ->
            data = SearchUtils.getSearchResult("author=Sergei Lukyanenko"));

        step("Проверяем, что результатов поиска > 0", () ->
            Assertions.assertTrue(data.getDocs().size() > 0, errorMessage));

        step("Проверяем, что в списке результатов по автору есть наша любимая книга", () ->
            Assertions.assertTrue(
                    data.getDocs().stream().anyMatch(libraryDoc -> libraryDoc.getTitle()
                            .equals(favoriteBook)),
                    "Искомая книга у автора не найдена"));
    }
}
