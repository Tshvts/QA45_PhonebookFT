package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage extends BasePage
{
    public LoginPage(WebDriver driver)
    {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,
                10), this);
    }  //constructor

    @FindBy(xpath = "//input[@name='email']")
    WebElement inputEmail;

    public void typeLoginForm(String email, String password)
    {
        inputEmail.sendKeys(email);
    }
}
