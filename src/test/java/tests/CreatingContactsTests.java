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
    public void login()
    {
        new HomePage(getDriver()).clickBtnLoginHeader();
        new LoginPage(getDriver()).typeLoginForm(new UserDto("shevt2827@gmail.com", "Password123!"));
        contactsPage = new ContactsPage(getDriver());
        addContactPage = new AddContactPage(getDriver());
        contactsPage.clickBtnAdd();
    }

    @Test
    public void createContactPositiveTest()
    {
        int i = new Random().nextInt(100, 999);
        ContactDtoLombok contact = ContactDtoLombok.builder()
                .name("Anya_" + i)
                .lastName("Petrova")
                .phone("0504448" + i)
                .email("petrova56@gmail.com")
                .address("Tel Aviv, Herzl, 15-97")
                .description("English teacher")
                .build();
        addContactPage.typeContactForm(contact);
        addContactPage.clickBtnSave();
        Assert.assertTrue(addContactPage.validateLastElementContactList(contact));
    }

    //Homework 10
    @Test
    public void createContactNegativeTest_emptyName()
    {
        int i = new Random().nextInt(100, 999);
        ContactDtoLombok contact = ContactDtoLombok.builder()
                .name("")
                .lastName("Petrova"+ i)
                .phone("0504448" + i)
                .email("petrova56@gmail.com")
                .address("Tel Aviv, Herzl, 15-97")
                .description("English teacher")
                .build();
        addContactPage.typeContactForm(contact);
        addContactPage.clickBtnSave();
        Assert.assertTrue(addContactPage.validateAddContactFormIsDisplayed());
    }

    @Test
    public void createContactNegativeTest_emptyLastName()
    {
        int i = new Random().nextInt(100, 999);
        ContactDtoLombok contact = ContactDtoLombok.builder()
                .name("Anya" + i)
                .lastName("")
                .phone("0504448" + i)
                .email("petrova56@gmail.com")
                .address("Tel Aviv, Herzl, 15-97")
                .description("English teacher")
                .build();
        addContactPage.typeContactForm(contact);
        addContactPage.clickBtnSave();
        Assert.assertTrue(addContactPage.validateAddContactFormIsDisplayed());
    }

    @Test
    public void createContactNegativeTest_wrongPhone()
    {
        int i = new Random().nextInt(100, 999);
        ContactDtoLombok contact = ContactDtoLombok.builder()
                .name("Anya" + i)
                .lastName("Petrova")
                .phone("05044")
                .email("petrova56@gmail.com")
                .address("Tel Aviv, Herzl, 15-97")
                .description("English teacher")
                .build();
        addContactPage.typeContactForm(contact);
        addContactPage.clickBtnSave();
        addContactPage.closeAlert();
        Assert.assertTrue(addContactPage.validateAddContactFormIsDisplayed());
    }

    @Test
    public void createContactNegativeTest_wrongEmail()
    {
        int i = new Random().nextInt(100, 999);
        ContactDtoLombok contact = ContactDtoLombok.builder()
                .name("Anya" + i)
                .lastName("Petrova")
                .phone("0504448" + i)
                .email("petrova56com")
                .address("Tel Aviv, Herzl, 15-97")
                .description("English teacher")
                .build();
        addContactPage.typeContactForm(contact);
        addContactPage.clickBtnSave();
        addContactPage.closeAlert();
        Assert.assertTrue(addContactPage.validateAddContactFormIsDisplayed());
    }

    @Test
    public void createContactNegativeTest_emptyAddress()
    {
        int i = new Random().nextInt(100, 999);
        ContactDtoLombok contact = ContactDtoLombok.builder()
                .name("Anya" + i)
                .lastName("Petrova")
                .phone("0504448" + i)
                .email("petrova56@gmail.com")
                .address("")
                .description("English teacher")
                .build();
        addContactPage.typeContactForm(contact);
        addContactPage.clickBtnSave();
        Assert.assertTrue(addContactPage.validateAddContactFormIsDisplayed());
    }
}
