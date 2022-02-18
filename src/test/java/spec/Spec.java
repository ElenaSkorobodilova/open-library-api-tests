package spec;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static filters.LogFilter.logFilter;
import static io.restassured.RestAssured.with;

public class Spec {
    public static String testUrl = "http://openlibrary.org/";

    public static RequestSpecification request = with()
            .filter(logFilter().withCustomTemplates())
            .baseUri(testUrl)
            .log().all()
            .contentType(ContentType.JSON);

    public static ResponseSpecification response = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();
}
