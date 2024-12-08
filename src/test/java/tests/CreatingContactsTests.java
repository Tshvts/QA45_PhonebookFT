package tests;

import dto.ContactDtoLombok;
import dto.UserDto;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AddContactPage;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;

import java.util.Random;

public class CreatingContactsTests extends ApplicationManager
{
    ContactsPage contactsPage;
    AddContactPage addContactPage;

    @BeforeMethod
    public void registration()
    {
        int i = new Random().nextInt(1000);
        new HomePage(getDriver()).clickBtnLoginHeader();
        new LoginPage(getDriver()).typeRegistrationForm("frosiaH_"+i+"@gmail.com", "Trjjs199!!");
    }

    @Test
    public void createContactPositiveTest()
    {
        ContactDtoLombok contact = ContactDtoLombok.builder()
                .name("Anya")
                .lastName("Petrova")
                .phone("0504448967")
                .email("petrova56@gmail.com")
                .address("Tel Aviv, Herzl, 15-97")
                .description("English teacher")
                .build();
        contactsPage = new ContactsPage(getDriver());
        addContactPage = new AddContactPage(getDriver());
        contactsPage.clickBtnAdd();
        addContactPage.typeContactForm(contact);
        addContactPage.clickBtnSave();
        Assert.assertTrue(addContactPage.isContactExisted("0504448967"));
    }
}
