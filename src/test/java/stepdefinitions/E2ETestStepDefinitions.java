package stepdefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import utilities.Driver;

import static base_urls.CLBaseUrl.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static utilities.ObjectMapperUtils.getJsonNode;
import static utilities.ObjectMapperUtils.updateJsonNode;

public class E2ETestStepDefinitions {

    public static String email;
    public static String firstname;
    public static String lastname;

    @Given("user goes to url {string}")
    public void user_goes_to_url(String url) {
        Driver.getDriver().get(url);
    }

    @When("user clicks on sign up button")
    public void user_clicks_on_sign_up_button() {
        Driver.getDriver().findElement(By.id("signup")).click();
    }

    @When("user fills the form and submit")
    public void user_fills_the_form_and_submit() {
        firstname = Faker.instance().name().firstName();
        lastname = Faker.instance().name().lastName();
        email = Faker.instance().internet().emailAddress();
        Driver.getDriver().findElement(By.id("firstName")).sendKeys(firstname);
        Driver.getDriver().findElement(By.id("lastName")).sendKeys(lastname);
        Driver.getDriver().findElement(By.id("email")).sendKeys(email);
        Driver.getDriver().findElement(By.id("password")).sendKeys("Password.12345", Keys.ENTER);
    }

    @Then("close browser")
    public void closeBrowser() {
        Driver.closeDriver();
    }

    @When("send the get request to get user profile")
    public void sendTheGetRequestToGetUserProfile() {
        response = given(spec).get("/users/me");
        response.prettyPrint();
    }

    @And("response body should contain corresponding user")
    public void responseBodyShouldContainCorrespondingUser() {
        response.then().body("firstName", equalTo(firstname),
                "lastName", equalTo(lastname),
                "email", equalTo(email));
    }

    @When("send patch request to update the user")
    public void sendPatchRequestToUpdateTheUser() {
        payload = getJsonNode("user_body");
        email = Faker.instance().internet().emailAddress();
        firstname = getJsonNode("user_body").get("firstName").asText();
        lastname = getJsonNode("user_body").get("lastName").asText();
        updateJsonNode(payload, "email", email);
        response = given(spec).body(payload).patch("/users/me");
        response.prettyPrint();
    }

    @When("send the delete request to delete user")
    public void sendTheDeleteRequestToDeleteUser() {
        response = given(spec).delete("/users/me");
    }

}