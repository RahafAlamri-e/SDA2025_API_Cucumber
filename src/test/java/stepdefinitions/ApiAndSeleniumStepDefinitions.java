package stepdefinitions;

import base_urls.CLBaseUrl;
import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import pages.AllPages;
import utilities.Driver;

import static base_urls.CLBaseUrl.response;
import static base_urls.CLBaseUrl.spec;
import static io.restassured.RestAssured.given;

public class ApiAndSeleniumStepDefinitions {

    AllPages pages = new AllPages();
    static String testContactName;
    static String testContactLastName;
    static String testEmail;
    static String idOfAddedContact;
    static String url = "https://thinking-tester-contact-list.herokuapp.com/";

    @Given("sign in via API")
    public void sign_in_via_api() throws InterruptedException {
        Driver.getDriver().get("https://thinking-tester-contact-list.herokuapp.com/");//First go to homepage, then add the cookie!!!
        Driver.getDriver().manage().addCookie(new Cookie("token", CLBaseUrl.getToken()));
        Thread.sleep(2000);
    }

    @When("add contact via Selenium")
    public void add_contact_via_selenium() {

        Driver.getDriver().get("https://thinking-tester-contact-list.herokuapp.com/addContact");

        testContactName = Faker.instance().name().firstName();
        testContactLastName = Faker.instance().name().lastName();
        testEmail = Faker.instance().internet().emailAddress();

        pages
                .getAddContactPage()
                .enterFirstname(testContactName)
                .enterLastname(testContactLastName)
                .enterEmail(testEmail)
                .clickSubmit();
    }

    @Then("assert the contact is added")
    public void assert_the_contact_is_added() {
        //Homework
        WebElement addedContact=

        pages
                .getContactListPage()
                .getAddedContact(testEmail,testContactName+ " " + testContactLastName);
        idOfAddedContact = addedContact.findElement(By.xpath(".//td")).getAttribute("textContent");;
        Assert.assertTrue(addedContact.isDisplayed());
    }

    @When("user get id of created contact")
    public void userGetIdOfCreatedContact() {
        WebElement addedContact=
                pages
                        .getContactListPage()
                        .getAddedContact(testEmail,testContactName+ " " + testContactLastName);
        idOfAddedContact = addedContact.findElement(By.xpath(".//td")).getAttribute("textContent");;
        System.out.println("idOfAddedContact = " + idOfAddedContact);

    }

    @And("delete created contact through api")
    public void deleteCretedContactThrougApi() {
        response =given(spec).when().delete("/contacts/"+idOfAddedContact);
        response.prettyPrint();

    }

    @Then("assert the contact is deleted")
    public void assertTheContactIsDeleted() {
        response
                .then()
                .statusCode(200);
                Assert.assertEquals(response.asString(),"Contact deleted");
    }

    @When("user navigates to {string} endpoint")
    public void userNavigatesToEndpoint(String endpoint) {
        Driver.getDriver().get(url+endpoint);
    }
}
