package tests;

import data_provider.DPAddContact;
import dto.ContactDtoLombok;
import dto.UserDto;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AddContactPage;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;
import utils.TestNGListener;

import java.util.Random;

@Listeners(TestNGListener.class)

public class CreatingContactsTests extends ApplicationManager
{
    SoftAssert softAssert = new SoftAssert();
    ContactsPage contactsPage;
    AddContactPage addContactPage;

    @BeforeMethod
    public void login()
    {
        new HomePage(getWebDriver()).clickBtnLoginHeader();
        new LoginPage(getWebDriver()).typeLoginForm(new UserDto("shevt2827@gmail.com", "Password123!"));
        contactsPage = new ContactsPage(getWebDriver());
        addContactPage = new AddContactPage(getWebDriver());
        contactsPage.clickBtnAdd();
    }

    @Test()
    public void createContactPositiveTest()
    {
        logger.info("CREATE CONTACT POSITIVE TEST");
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
        Assert.assertFalse(addContactPage.validateUrlContacts());
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
        String message = addContactPage.closeAlert();
        System.out.println(message);
        softAssert.assertTrue(message.contains("Phone number must contain only digits!"));
        softAssert.assertTrue(addContactPage.validateAddContactFormIsDisplayed());
        softAssert.assertAll();
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


    //HW 12

    @Test(dataProvider = "addContactPositive", dataProviderClass = DPAddContact.class)
    public void addContactPositiveTestDP(ContactDtoLombok contact)
    {
        addContactPage.typeContactForm(contact);
        addContactPage.clickBtnSave();
        Assert.assertTrue(addContactPage.validateLastElementContactList(contact));
    }

    @Test(dataProvider = "addContactNegative_EmptyName", dataProviderClass = DPAddContact.class)
    public void addContactNegativeTestDP_EmptyName(ContactDtoLombok contact)
    {
        addContactPage.typeContactForm(contact);
        addContactPage.clickBtnSave();
        Assert.assertFalse(addContactPage.validateUrlContacts());
    }

    @Test(dataProvider = "addContactNegative_EmptyLastName", dataProviderClass = DPAddContact.class)
    public void addContactNegativeTestDP_EmptyLastName(ContactDtoLombok contact)
    {
        addContactPage.typeContactForm(contact);
        addContactPage.clickBtnSave();
        Assert.assertTrue(addContactPage.validateAddContactFormIsDisplayed());
    }

    @Test(dataProvider = "addContactNegative_WrongPhone", dataProviderClass = DPAddContact.class)
    public void addContactNegativeTestDP_WrongPhone(ContactDtoLombok contact)
    {
        addContactPage.typeContactForm(contact);
        addContactPage.clickBtnSave();
        String message = addContactPage.closeAlert();
        System.out.println(message);
        softAssert.assertTrue(message.contains("Phone number must contain only digits!"));
        softAssert.assertTrue(addContactPage.validateAddContactFormIsDisplayed());
        softAssert.assertAll();
    }

    @Test(dataProvider = "addContactNegative_WrongEmail", dataProviderClass = DPAddContact.class)
    public void addContactNegativeTestDP_WrongEmail(ContactDtoLombok contact)
    {
        addContactPage.typeContactForm(contact);
        addContactPage.clickBtnSave();
        addContactPage.closeAlert();
        Assert.assertTrue(addContactPage.validateAddContactFormIsDisplayed());
    }

    @Test(dataProvider = "addContactNegative_EmptyAddress", dataProviderClass = DPAddContact.class)
    public void addContactNegativeTestDP_EmptyAddress(ContactDtoLombok contact)
    {
        addContactPage.typeContactForm(contact);
        addContactPage.clickBtnSave();
        Assert.assertTrue(addContactPage.validateAddContactFormIsDisplayed());
    }
}
