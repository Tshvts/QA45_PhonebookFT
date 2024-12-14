package pages;

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
        btnAdd.click();
    }

    public void deleteFirstContact()
    {
        clickWait(firstContact,3);
        clickWait(btnRemove,3);
        pause(3);
    }

    public int quantityContacts()
    {
        return new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='contact-item_card__2SOIM']"))).size();
    }
}
