package pages;

import dto.UserDto;
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

    @FindBy(xpath = "//input[@name='password']")
    WebElement inputPwd;

    @FindBy(xpath = "//button[text()='Login']")
    WebElement btnLogin;

    @FindBy(xpath = "//button[text()='Registration']")
    WebElement btnRegister;

    public void typeLoginForm(UserDto user)
    {
        inputEmail.sendKeys(user.getEmail());
        inputPwd.sendKeys(user.getPassword());

        pause(2);

        btnLogin.click();
        pause(2);
    }

    public void typeRegistrationForm(String email, String password)
    {
        inputEmail.sendKeys(email);
        inputPwd.sendKeys(password);

        pause(2);

        btnRegister.click();

        pause(2);
    }
}
