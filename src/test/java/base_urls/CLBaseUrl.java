package base_urls;

import io.cucumber.java.Before;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;


import static io.restassured.RestAssured.given;

public class CLBaseUrl {

    public static RequestSpecification spec;
    private static String baseUrl = "https://thinking-tester-contact-list.herokuapp.com";

    //This method will run before each scenario:
    @Before//import io.cucumber.java.Before; => From JUnit or TestNG
    public void setSpec() {
        spec = new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + getToken())
                .build();
    }

    //OR:
    /*
    static {//Whatever we call from this class, first this block will run.
        spec = new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + getToken())
                .build();
    }
     */

    private static String getToken() {
        String credentials = "{\n" +
                "    \"email\": \"rererre@rreerr.com\",\n" +
                "    \"password\": \"12345678\"\n" +
                "}";
        return given()
                .body(credentials)
                .contentType(ContentType.JSON)
                .post(baseUrl + "/users/login")
                .jsonPath()
                .getString("token");
    }

}
