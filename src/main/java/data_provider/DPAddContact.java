package data_provider;

import dto.ContactDtoLombok;
import org.testng.annotations.DataProvider;

import static utils.RandomUtils.*;

public class DPAddContact
{
    @DataProvider
    public ContactDtoLombok[] addContactPositive()
    {
        ContactDtoLombok contact1 = ContactDtoLombok.builder()
                .name(generateString(6))
                .lastName(generateString(8))
                .phone(generatePhone(10))
                .email(generateEmail(9))
                .address(generateAddress(11, 2))
                .build();

        ContactDtoLombok contact2 = ContactDtoLombok.builder()
                .name(generateString(4))
                .lastName(generateString(5))
                .phone(generatePhone(10))
                .email(generateEmail(6))
                .address(generateAddress(7, 2))
                .build();

        ContactDtoLombok contact3 = ContactDtoLombok.builder()
                .name(generateString(7))
                .lastName(generateString(9))
                .phone(generatePhone(10))
                .email(generateEmail(11))
                .address(generateAddress(6, 2))
                .build();

        return new ContactDtoLombok[]{contact1, contact2, contact3};
    }

    @DataProvider
    public ContactDtoLombok[] addContactNegative_EmptyName()
    {
        ContactDtoLombok contact1 = ContactDtoLombok.builder()
                .name("")
                .lastName(generateString(8))
                .phone(generatePhone(10))
                .email(generateEmail(9))
                .address(generateAddress(11, 2))
                .build();

        ContactDtoLombok contact2 = ContactDtoLombok.builder()
                .name("")
                .lastName(generateString(5))
                .phone(generatePhone(10))
                .email(generateEmail(6))
                .address(generateAddress(7, 2))
                .build();

        ContactDtoLombok contact3 = ContactDtoLombok.builder()
                .name("")
                .lastName(generateString(9))
                .phone(generatePhone(10))
                .email(generateEmail(11))
                .address(generateAddress(6, 2))
                .build();

        return new ContactDtoLombok[]{contact1, contact2, contact3};
    }

    @DataProvider
    public ContactDtoLombok[] addContactNegative_EmptyLastName()
    {
        ContactDtoLombok contact1 = ContactDtoLombok.builder()
                .name(generateString(9))
                .lastName("")
                .phone(generatePhone(10))
                .email(generateEmail(9))
                .address(generateAddress(11, 2))
                .build();

        ContactDtoLombok contact2 = ContactDtoLombok.builder()
                .name(generateString(8))
                .lastName("")
                .phone(generatePhone(10))
                .email(generateEmail(6))
                .address(generateAddress(7, 2))
                .build();

        ContactDtoLombok contact3 = ContactDtoLombok.builder()
                .name(generateString(5))
                .lastName("")
                .phone(generatePhone(10))
                .email(generateEmail(11))
                .address(generateAddress(6, 2))
                .build();

        return new ContactDtoLombok[]{contact1, contact2, contact3};
    }

    @DataProvider
    public ContactDtoLombok[] addContactNegative_WrongPhone()
    {
        ContactDtoLombok contact1 = ContactDtoLombok.builder()
                .name(generateString(9))
                .lastName(generateString(5))
                .phone(generatePhone(2))
                .email(generateEmail(9))
                .address(generateAddress(11, 2))
                .build();

        ContactDtoLombok contact2 = ContactDtoLombok.builder()
                .name(generateString(8))
                .lastName(generateString(9))
                .phone(generateWrongPhone(5))
                .email(generateEmail(6))
                .address(generateAddress(7, 2))
                .build();

        ContactDtoLombok contact3 = ContactDtoLombok.builder()
                .name(generateString(5))
                .lastName(generateString(12))
                .phone("            ")
                .email(generateEmail(11))
                .address(generateAddress(6, 2))
                .build();

        return new ContactDtoLombok[]{contact1, contact2, contact3};
    }

    @DataProvider
    public ContactDtoLombok[] addContactNegative_WrongEmail()
    {
        ContactDtoLombok contact1 = ContactDtoLombok.builder()
                .name(generateString(9))
                .lastName(generateString(5))
                .phone(generatePhone(10))
                .email(generateWrongEmail(6))
                .address(generateAddress(11, 2))
                .build();

        ContactDtoLombok contact2 = ContactDtoLombok.builder()
                .name(generateString(8))
                .lastName(generateString(9))
                .phone(generatePhone(10))
                .email(generateWrongEmail(8))
                .address(generateAddress(7, 2))
                .build();

        ContactDtoLombok contact3 = ContactDtoLombok.builder()
                .name(generateString(5))
                .lastName(generateString(11))
                .phone(generatePhone(10))
                .email("       ")
                .address(generateAddress(6, 2))
                .build();

        return new ContactDtoLombok[]{contact1, contact2, contact3};
    }

    @DataProvider
    public ContactDtoLombok[] addContactNegative_EmptyAddress()
    {
        ContactDtoLombok contact1 = ContactDtoLombok.builder()
                .name(generateString(9))
                .lastName(generateString(10))
                .phone(generatePhone(10))
                .email(generateEmail(9))
                .address("")
                .build();

        ContactDtoLombok contact2 = ContactDtoLombok.builder()
                .name(generateString(8))
                .lastName(generateString(8))
                .phone(generatePhone(10))
                .email(generateEmail(6))
                .address("")
                .build();

        ContactDtoLombok contact3 = ContactDtoLombok.builder()
                .name(generateString(5))
                .lastName(generateString(3))
                .phone(generatePhone(10))
                .email(generateEmail(11))
                .address("")
                .build();

        return new ContactDtoLombok[]{contact1, contact2, contact3};
    }
}
