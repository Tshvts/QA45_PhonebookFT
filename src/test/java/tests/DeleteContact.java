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

@Listeners(TestNGListener.class)

public class DeleteContact extends ApplicationManager
{
    ContactsPage contactsPage;
    @BeforeMethod
    public void login()
    {
        new HomePage(getDriver()).clickBtnLoginHeader();
        new LoginPage(getDriver()).typeLoginForm(new UserDto("shevt2827@gmail.com", "Password123!"));
        contactsPage = new ContactsPage(getDriver());
    }

    @Test
    public void deleteContactPositiveTest()
    {
        int quantityBeforeDelete = contactsPage.quantityContacts();
        System.out.println("Quantity Before Delete: " + quantityBeforeDelete);
        contactsPage.deleteFirstContact();
        int quantityAfterDelete = contactsPage.quantityContacts();
        System.out.println("Quantity After Delete:" + quantityAfterDelete);
        Assert.assertEquals(quantityBeforeDelete-1, quantityAfterDelete);
    }
}
