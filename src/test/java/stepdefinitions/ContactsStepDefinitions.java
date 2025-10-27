package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static base_urls.CLBaseUrl.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ContactsStepDefinitions {


    static String contactId;

    @When("send the get request to get all contacts")
    public void send_the_get_request_to_get_all_contacts() {
        //https://thinking-tester-contact-list.herokuapp.com/contacts
        response = given(spec).get("/contacts");
        response.prettyPrint();
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

    @When("send the delete request to delete contact")
    public void send_the_delete_request_to_delete_contact() {
        //https://thinking-tester-contact-list.herokuapp.com/contacts/
        response = given(spec).delete("/contacts/" + contactId);
        response.prettyPrint();
    }

}