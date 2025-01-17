package okhttp;

import dto.ContactDtoLombok;
import dto.ContactsDto;
import dto.TokenDto;
import dto.UserDtoLombok;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.BaseApi;

import java.io.IOException;

public class GetAllUserContactsTests implements BaseApi
{
    UserDtoLombok userDtoLombok;
    SoftAssert softAssert = new SoftAssert();
    TokenDto tokenDto;
    ContactDtoLombok contactDtoLombok;
    ContactsDto contactsDto;

    @BeforeClass
    public void login()
    {
        userDtoLombok = UserDtoLombok.builder()
                .username("o3hfpb1dov@gmail.com")
                .password("Password123!")
                .build();

        RequestBody requestBody = RequestBody.create(GSON.toJson(userDtoLombok), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + LOGIN)
                .post(requestBody)
                .build();

        try(Response response = OK_HTTP_CLIENT.newCall(request).execute())
        {
            if(response.isSuccessful())
            {
                System.out.println("Login is successful + " + userDtoLombok.toString());
                tokenDto = GSON.fromJson(response.body().string(), TokenDto.class);
                System.out.println(tokenDto);
            }

            else
            {
                Assert.fail("Login is failed: " + response.code());
            }
        }

        catch (IOException e)
        {
            Assert.fail("Created exception: ");
        }
    }

    @Test
    public void getAllUserContactsPositiveTest()
    {
        Request request = new Request.Builder()
                .url(BASE_URL + ADD_CONTACT)
                .addHeader(AUTH,tokenDto.getToken())
                .get()
                .build();

        try(Response response = OK_HTTP_CLIENT.newCall(request).execute())
        {
            if (response.isSuccessful())
            {
                contactsDto = GSON.fromJson(response.body().string(), ContactsDto.class);
                for(ContactDtoLombok contacts: contactsDto.getContacts())
                {
                    System.out.println(contacts.toString());
                }
            }

            else
            {
                System.out.println("Get isn't successful: " + response.code());
            }
        }

        catch (IOException e)
        {
            e.printStackTrace();
            Assert.fail("Created exception");
        }
    }
}
