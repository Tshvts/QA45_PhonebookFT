package pages;

import dto.ContactDtoLombok;
import dto.UserDto;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class AddContactPage extends BasePage
{
    public AddContactPage(WebDriver driver)
    {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,
                10), this);
    }

    @FindBy(xpath = "//input[@placeholder='Name']")
    WebElement fieldName;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    WebElement fieldLastName;

    @FindBy(xpath = "//input[@placeholder='Phone']")
    WebElement fieldPhone;

    @FindBy(xpath = "//input[@placeholder='email']")
    WebElement fieldEmail;

    @FindBy(xpath = "//input[@placeholder='Address']")
    WebElement fieldAddress;

    @FindBy(xpath = "//input[@placeholder='description']")
    WebElement fieldDescription;

    @FindBy(xpath = "//b[text()='Save']")
    WebElement btnSave;

    @FindBy(xpath = "//div[@class='contact-item_card__2SOIM']")
    WebElement createdContact;

    public void typeContactForm(ContactDtoLombok contact)
    {
        fieldName.sendKeys(contact.getName());
        fieldLastName.sendKeys(contact.getLastName());
        fieldPhone.sendKeys(contact.getPhone());
        fieldEmail.sendKeys(contact.getEmail());
        fieldAddress.sendKeys(contact.getAddress());
        fieldDescription.sendKeys(contact.getDescription());
        pause(2);
    }

    public void clickBtnSave()
    {
        btnSave.click();
        pause(2);
    }

    public boolean isContactExisted(String text)
    {
        return isElementContainsText(createdContact,text);
    }


}
