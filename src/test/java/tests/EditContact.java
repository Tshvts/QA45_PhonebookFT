package tests;

import data_provider.DPContact;
import dto.ContactDtoLombok;
import dto.UserDto;
import manager.ApplicationManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;
import utils.TestNGListener;

import static utils.RandomUtils.*;

@Listeners(TestNGListener.class)

public class EditContact extends ApplicationManager
{
    ContactsPage contactsPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod(alwaysRun = true)
    public void login()
    {
        new HomePage(getDriver()).clickBtnLoginHeader();
        new LoginPage(getDriver()).typeLoginForm(new UserDto("shevt2827@gmail.com", "Password123!"));
        contactsPage = new ContactsPage(getDriver());
    }

    @Test
    public void editContactPositiveTest()
    {
        ContactDtoLombok contact = ContactDtoLombok.builder()
                .name(generateString(5))
                .lastName(generateString(10))
                .phone(generatePhone(10))
                .email(generateEmail(7))
                .address("Tel Aviv, Herzl," + generatePhone(2))
                .description("Eng") //field is a bug. I can't edit this field
                .build();
        contactsPage.editFirstContact(contact);
        contactsPage.clickBtnSave();
        contactsPage.validateContactCard(contact);
        softAssert.assertTrue(contactsPage.validateContactCard(contact));
    }

    @Test(dataProvider = "newContactDP", dataProviderClass = DPContact.class)
    public void editContactTestDP(ContactDtoLombok contact)
    {
        contactsPage.editFirstContact(contact);
        contactsPage.clickBtnSave();
        softAssert.assertTrue(contactsPage.validateContactCard(contact));
    }
}
