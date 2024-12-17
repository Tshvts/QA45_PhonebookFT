package tests;

import dto.ContactDtoLombok;
import dto.UserDto;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;

import java.util.Random;
import static utils.RandomUtils.*;

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
//        softAssert.assertTrue(contactsPage.contactCardIsDisplayed());
//        softAssert.assertTrue(contactsPage.validateChangesInContact(generateString(5)));
//        softAssert.assertAll();
        contactsPage.validateContactCard(contact);
        softAssert.assertTrue(contactsPage.validateContactCard(contact));
    }
}