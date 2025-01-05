package tests;

import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;
import utils.TestNGListener;

import java.util.Random;

@Listeners(TestNGListener.class)

public class RegistrationTests extends ApplicationManager
{
    @Test
    public void RegistrationPositiveTest()
    {
        int i = new Random().nextInt(1000);
        new HomePage(getWebDriver()).clickBtnLoginHeader();
        new LoginPage(getWebDriver()).typeRegistrationForm("frosiaH_"+i+"@gmail.com", "Trjjs199!!");
        Assert.assertTrue(new ContactsPage(getWebDriver()).isSignOutPresent());
    }

    @Test
    public void registrationNegativeTest_wrongPassword()
    {
        int i = new Random().nextInt(1000);
        new HomePage(getWebDriver()).clickBtnLoginHeader();
        new LoginPage(getWebDriver()).typeRegistrationForm("frosiaH_"+i+"@gmail.com", "Trj!!");
        new LoginPage(getWebDriver()).closeAlert();
        Assert.assertTrue(new LoginPage(getWebDriver()).validateErrorMessage("Registration failed with code 400"));
    }

    @Test
    public void registrationNegativeTest_wrongEmail()
    {
        int i = new Random().nextInt(1000);
        new HomePage(getWebDriver()).clickBtnLoginHeader();
        new LoginPage(getWebDriver()).typeRegistrationForm("frosiaH_"+i+"gmailcom", "Password123!");
        new LoginPage(getWebDriver()).closeAlert();
        Assert.assertTrue(new LoginPage(getWebDriver()).validateErrorMessage("Registration failed with code 400"));
    }

}
