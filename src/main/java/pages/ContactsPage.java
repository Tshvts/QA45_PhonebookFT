package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

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

    public void clickBtnSignOut()
    {
        pause(3);
        btnSignOut.click();
    }

    public boolean isSignOutPresent()
    {
        pause(2);
        return btnSignOut.isDisplayed();
    }

    public void clickBtnAdd()
    {
        btnAdd.click();
        pause(2);
    }
}
