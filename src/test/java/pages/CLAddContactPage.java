package pages;

import org.openqa.selenium.By;
import utilities.Driver;

public class CLAddContactPage {

    private By firstname = By.id("firstName");
    private By lastName = By.id("lastName");
    private By email = By.id("email");
    private By submit = By.id("submit");

    public CLAddContactPage enterFirstname(String firstname) {
        Driver.getDriver().findElement(this.firstname).sendKeys(firstname);
        return this;
    }

    public CLAddContactPage enterLastname(String lastname) {
        Driver.getDriver().findElement(this.lastName).sendKeys(lastname);
        return this;
    }

    public void clickSubmit() {
        Driver.getDriver().findElement(submit).click();
    }

    public CLAddContactPage enterEmail(String email) {
        Driver.getDriver().findElement(this.email).sendKeys(email);
        return this;
    }
}
