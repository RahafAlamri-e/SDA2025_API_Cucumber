package stepdefinitions;

import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static base_urls.CLBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static utilities.ObjectMapperUtils.getJsonNode;

public class ContactsStepDefinitions {

    Response response;
    static JsonNode payload;
    static String contactId;

    @When("send the get request to get all contacts")
    public void send_the_get_request_to_get_all_contacts() {
        //https://thinking-tester-contact-list.herokuapp.com/contacts
        response = given(spec).get("/contacts");
        response.prettyPrint();
    }

    @Then("status code should be {int}")
    public void status_code_should_be(Integer statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("response body should contain contact bodies")
    public void response_body_should_contain_contact_bodies() {
        response.then().body("", hasSize(greaterThan(0)));
    }

    @When("send post request to create contact")
    public void sendPostRequestToCreateContact() {
        //https://thinking-tester-contact-list.herokuapp.com/contacts
        response = given(spec).body(payload).post("/contacts");
        response.prettyPrint();
    }

    @And("response body should contain corresponding contact")
    public void responseBodyShouldContainCorrespondingContact() {

        response
                .then()
                .body(
                        "firstName", equalTo(payload.get("firstName").asText()),
                        "lastName", equalTo(payload.get("lastName").asText()),
                        "birthdate", equalTo(payload.get("birthdate").asText()),
                        "email", equalTo(payload.get("email").asText()),
                        "phone", equalTo(payload.get("phone").asText()),
                        "street1", equalTo(payload.get("street1").asText()),
                        "street2", equalTo(payload.get("street2").asText()),
                        "city", equalTo(payload.get("city").asText()),
                        "stateProvince", equalTo(payload.get("stateProvince").asText()),
                        "postalCode", equalTo(payload.get("postalCode").asText()),
                        "country", equalTo(payload.get("country").asText())
                );
        contactId = response.jsonPath().getString("_id");
    }

    @When("send the get request to get contact by id")
    public void send_the_get_request_to_get_contact_by_id() {
        //https://thinking-tester-contact-list.herokuapp.com/contacts/id
        System.out.println("contactId = " + contactId);
        response = given(spec).get("/contacts/" + contactId);
        response.prettyPrint();
    }


    @When("send put request to update contact")
    public void sendPutRequestToUpdateContact() {
        response = given(spec).body(payload).put("/contacts/" + contactId);
    }

    @Given("prepare payload named {string}")
    public void preparePayloadNamed(String fileName) {
        payload = getJsonNode(fileName);
        System.out.println(payload.toPrettyString());
    }

    @When("send patch request to update contact")
    public void sendPatchRequestToUpdateContact() {
        response = given(spec).body(payload).patch("/contacts/" + contactId);
        response.prettyPrint();
    }

    @Then("response body should contain updated field")
    public void responseBodyShouldContainUpdatedField() {
        response.then().body(
                "email", equalTo(payload.get("email").asText())
        );
    }

    @When("send delete request to delete created contact")
    public void sendDeleteRequestToDeleteCreatedContact() {
        response = given(spec).delete("/contacts/" + contactId);
        response.prettyPrint();
    }

    @Then("response body should be as {string}")
    public void responseBodyShouldBeAs(String responseStr) {
        response
                .then()
                .body(notNullValue())
                .body(equalToIgnoringCase(responseStr));
    }

    @Then("response body should return empty")
    public void responseBodyShouldReturnEmpty() {
       response
               .then()
               .body(emptyOrNullString());
    }
}
