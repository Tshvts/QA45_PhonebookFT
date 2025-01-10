package pages;

import dto.ContactDtoLombok;
import dto.UserDto;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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

    @FindBy(xpath = "//div[@class='contact-page_leftdiv__yhyke']/div/div[last()]")
    WebElement lastElementContactList;

    @FindBy(xpath = "//div[@class='add_main__1tbl_']")
    WebElement addContactForm;

    public void typeContactForm(ContactDtoLombok contact)
    {
        fieldName.sendKeys(contact.getName());
        fieldLastName.sendKeys(contact.getLastName());
        fieldPhone.sendKeys(contact.getPhone());
        fieldEmail.sendKeys(contact.getEmail());
        fieldAddress.sendKeys(contact.getAddress());
        //fieldDescription.sendKeys(contact.getDescription());
    }

    public void clickBtnSave()
    {
        clickWait(btnSave,3);
    }

    public boolean validateLastElementContactList(ContactDtoLombok contact)
    {
        return lastElementContactList.getText().contains(contact.getName());
    }

    public boolean validateAddContactFormIsDisplayed()
    {
        return addContactForm.isDisplayed();
    }

    public String closeAlert()
    {
        Alert alert = new WebDriverWait(driver, 5)
                .until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        alert.accept();
        return text;
    }

    public boolean validateUrlContacts()
    {
        return validateUrl("contacts", 5);
    }
}
