package data_provider;

import dto.ContactDtoLombok;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static utils.RandomUtils.*;
import static utils.RandomUtils.generatePhone;

public class DPContact
{
    @DataProvider
    public ContactDtoLombok[] newContactDP()
    {
        ContactDtoLombok contact1 = ContactDtoLombok.builder()
                .name(generateString(5))
                .lastName(generateString(10))
                .phone(generatePhone(10))
                .email(generateEmail(7))
                .address("Tel Aviv, Herzl," + generatePhone(2))
                .build();

        ContactDtoLombok contact2 = ContactDtoLombok.builder()
                .name(generateString(5))
                .lastName(generateString(10))
                .phone(generatePhone(10))
                .email(generateEmail(7))
                .address("Tel Aviv, Herzl," + generatePhone(2))
                .build();

        ContactDtoLombok contact3 = ContactDtoLombok.builder()
                .name(generateString(5))
                .lastName(generateString(10))
                .phone(generatePhone(10))
                .email(generateEmail(7))
                .address("Tel Aviv, Herzl," + generatePhone(2))
                .build();
        return new ContactDtoLombok[]{contact1, contact2, contact3};
    }

    @DataProvider
    public Iterator<ContactDtoLombok> newContactDPFile()
    {
        List<ContactDtoLombok> conactList = new ArrayList<>();
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader("src/main/resources/data_provider/contact_table.csv"));
            String line = bufferedReader.readLine();
            while(line != null) {
                String[] splitArray = line.split(",");
                conactList.add(ContactDtoLombok.builder()
                        .name(splitArray[0])
                        .lastName(splitArray[1])
                        .phone(splitArray[2])
                        .email(splitArray[3])
                        .address(splitArray[4])
                        .description(splitArray[5])
                        .build());
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return conactList.listIterator();
    }
}
