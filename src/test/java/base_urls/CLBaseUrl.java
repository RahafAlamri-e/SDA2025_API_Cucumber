package base_urls;

import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.java.Before;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.ObjectMapperUtils;


import static io.restassured.RestAssured.given;
import static stepdefinitions.E2ETestStepDefinitions.email;

public class CLBaseUrl {

    public static Response response;
    public static JsonNode payload;
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

    public static String getToken() {
        JsonNode credentials = ObjectMapperUtils.getJsonNode("credentials");
        if (email != null) {
            ObjectMapperUtils.updateJsonNode(credentials, "email", email);
            ObjectMapperUtils.updateJsonNode(credentials, "password", "Password.12345");
        }
        return given()
                .body(credentials)
                .contentType(ContentType.JSON)
                .post(baseUrl + "/users/login")
                .jsonPath()
                .getString("token");
    }

}