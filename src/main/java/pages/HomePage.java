package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import static utils.PropertiesReader.*;

public class HomePage extends BasePage
{
    public HomePage(WebDriver driver)
    {
        setDriver(driver);
        driver.get(getProperty("login.properties","urlStart"));
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,
                10), this);
    }  //constructor

    @FindBy(xpath = "//a[text()='LOGIN']")
    WebElement btnLogin;

    public void clickBtnLoginHeader()
    {
        btnLogin.click();
    }
}
