package tests;

import dto.UserDto;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;
import utils.TestNGListener;

import java.util.Random;

@Listeners(TestNGListener.class)

public class LoginTests extends ApplicationManager
{
    private String email, password;

    @BeforeMethod
    public void Registration()
    {
        int i = new Random().nextInt(1000);
        email = "tanyshvtsfid_"+i+"@gmail.com";
        password = "Password123!!";
        new HomePage(getWebDriver()).clickBtnLoginHeader();
        new LoginPage(getWebDriver()).typeRegistrationForm(email, password);
        new ContactsPage(getWebDriver()).clickBtnSignOut();
    }

    @Test
    public void loginPositiveTest()
    {
        UserDto user = new UserDto(email, password);
        new LoginPage(getWebDriver()).typeLoginForm(user);
        Assert.assertTrue(new ContactsPage(getWebDriver()).isSignOutPresent());
    }

    @Test
    public void loginNegativeTest_emptyEmail()
    {
        UserDto user = new UserDto(" ", password);
        new LoginPage(getWebDriver()).typeLoginForm(user);
        new LoginPage(getWebDriver()).closeAlert();
        Assert.assertTrue(new LoginPage(getWebDriver()).validateErrorMessageLog("Login Failed with code 401"));
    }

}
