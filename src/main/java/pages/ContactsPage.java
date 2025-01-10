package pages;

import dto.ContactDtoLombok;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ContactsPage extends BasePage
{
    public ContactsPage(WebDriver driver)
    {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,
                10), this);
    }  //constructor

    @FindBy(xpath = "//button[text()='Sign Out']")
    WebElement btnSignOut;

    @FindBy(xpath = "//a[text()='ADD']")
    WebElement btnAdd;

    @FindBy(xpath = "//div[@class='contact-item_card__2SOIM']")
    WebElement firstContact;

    @FindBy(xpath = "//button[text()='Remove']")
    WebElement btnRemove;

    @FindBy(xpath = "//button[text()='Edit']")
    WebElement btnEdit;

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

    @FindBy(xpath = "//button[text()='Save']")
    WebElement btnSave;

    @FindBy(xpath = "//div[@class='contact-item-detailed_card__50dTS']")
    WebElement contactCard;

    public void clickBtnSignOut()
    {
        clickWait(btnSignOut, 5);
    }

    public boolean isSignOutPresent()
    {
        return btnSignOut.isDisplayed();
    }

    public void clickBtnAdd()
    {
        clickWait(btnAdd,5);
    }

    public void deleteFirstContact()
    {
        clickWait(firstContact,3);
        clickWait(btnRemove,3);
        pause(3);
    }

    public int quantityContacts()
    {
        return new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='contact-item_card__2SOIM']"))).size();
    }

    //HOMEWORK 11

    public void editFirstContact(ContactDtoLombok contact)
    {
        clickWait(firstContact,3);
        clickWait(btnEdit,3);
        pause(3);

        fieldName.clear();
        fieldName.sendKeys(contact.getName());

        fieldLastName.clear();
        fieldLastName.sendKeys(contact.getLastName());

        fieldPhone.clear();
        fieldPhone.sendKeys(contact.getPhone());

        fieldEmail.clear();
        fieldEmail.sendKeys(contact.getEmail());

        fieldAddress.clear();
        fieldAddress.sendKeys(contact.getAddress());
    }

    public void clickBtnSave()
    {
        clickWait(btnSave, 5);
    }

    public boolean validateContactCard(ContactDtoLombok contact)
    {
         System.out.println("old contact:" + contactCard.getText());
         new WebDriverWait(driver, 5).until(ExpectedConditions.textToBePresentInElement(contactCard,contact.getName()));
         System.out.println("new contact:" + contactCard.getText());
         String cardContactText = contactCard.getText();
         return (cardContactText.contains(contact.getName())
                 && cardContactText.contains(contact.getLastName())
                 && cardContactText.contains(contact.getPhone())
                 && cardContactText.contains(contact.getEmail())
                 && cardContactText.contains(contact.getAddress()));
    }
}
