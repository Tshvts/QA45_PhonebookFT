package pages;

import dto.UserDto;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

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

    @FindBy(xpath = "//input[@name='password']")
    WebElement inputPwd;

    @FindBy(xpath = "//button[text()='Login']")
    WebElement btnLogin;

    @FindBy(xpath = "//button[text()='Registration']")
    WebElement btnRegister;

    @FindBy(xpath = "//div[text()='Login Failed with code 401']")
    WebElement errorMessage;

    @FindBy(xpath = "//div[text()='Registration failed with code 400']")
    WebElement errorMessageRegis;

    public void typeLoginForm(UserDto user)
    {
        inputEmail.sendKeys(user.getEmail());
        inputPwd.sendKeys(user.getPassword());

        btnLogin.click();
    }

    public void typeRegistrationForm(String email, String password)
    {
        inputEmail.sendKeys(email);
        inputPwd.sendKeys(password);

        btnRegister.click();
    }

    public void closeAlert()
    {
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }

    public boolean validateErrorMessage(String text)
    {
         return isElementContainsText(errorMessageRegis, text);
    }

    public boolean validateErrorMessageLog(String text)
    {
         return isElementContainsText(errorMessage, text);
    }
}
