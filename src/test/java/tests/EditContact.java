package tests;

import dto.ContactDtoLombok;
import dto.UserDto;
import manager.ApplicationManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;

import java.util.Random;

public class EditContact extends ApplicationManager
{
    ContactsPage contactsPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void login()
    {
        new HomePage(getDriver()).clickBtnLoginHeader();
        new LoginPage(getDriver()).typeLoginForm(new UserDto("shevt2827@gmail.com", "Password123!"));
        contactsPage = new ContactsPage(getDriver());
    }

    @Test
    public void editContactPositiveTest()
    {
        int i = new Random().nextInt(100, 999);
        ContactDtoLombok contact = ContactDtoLombok.builder()
                .name("Sasha" + i)
                .lastName("Ivanova" + i)
                .phone("0504232" + i)
                .email(i + "_ivanova56@gmail.com")
                .address("Tel Aviv, Herzl, 15-" + i)
                .description("Eng") //field is a bug. I can't edit this field
                .build();
        contactsPage.editFirstContact(contact);
        contactsPage.clickBtnSave();
        softAssert.assertTrue(contactsPage.contactCardIsDisplayed());
        softAssert.assertTrue(contactsPage.validateChangesInContact("Sasha" + i));
        softAssert.assertAll();
    }
}
