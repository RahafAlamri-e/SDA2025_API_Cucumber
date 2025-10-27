package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static base_urls.CLBaseUrl.payload;
import static base_urls.CLBaseUrl.response;
import static org.hamcrest.Matchers.*;
import static utilities.ObjectMapperUtils.getJsonNode;

public class CommonStepDefinitions {

    @Then("status code should be {int}")
    public void status_code_should_be(Integer statusCode) {
        response.then().time(lessThan(3000L)).statusCode(statusCode);
    }

    @Given("prepare payload named {string}")
    public void preparePayloadNamed(String fileName) {
        payload = getJsonNode(fileName);
        //System.out.println(payload.toPrettyString());
    }

    @And("response body should be empty")
    public void responseBodyShouldBeEmpty() {
        response.then().body(emptyString());
    }

    @Then("response body should contain {string}")
    public void response_body_should_contain(String string) {
        response.then().body(containsString(string));
    }


}