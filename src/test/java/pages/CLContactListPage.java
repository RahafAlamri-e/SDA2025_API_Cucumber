package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Driver;

public class CLContactListPage {

    private By addContact = By.id("add-contact");
    private String addedContact = "//tr[contains(., '%s') and contains(., '%s') ]";

    public WebElement getAddedContact(String email, String fullName) {
        //Homework
        String contactBy = String.format(addedContact,email,fullName);
        return Driver.getDriver().findElement(By.xpath(contactBy));

    }


    public void clickAddContact() {
        Driver.getDriver().findElement(addContact).click();
    }

}
