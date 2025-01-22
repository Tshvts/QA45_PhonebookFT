package okhttp;

import dto.ContactDtoLombok;
import dto.ResponseMessageDto;
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
import static utils.RandomUtils.*;

public class AddNewContactTests implements BaseApi
{
    UserDtoLombok userDtoLombok;
    SoftAssert softAssert = new SoftAssert();
    TokenDto tokenDto;

    @BeforeClass(alwaysRun = true)
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

    @Test(invocationCount = 3, groups = "smoke")
    public void addNewContactPositiveTest()
    {
        ContactDtoLombok contactDtoLombok = ContactDtoLombok.builder()
                .name(generateString(5))
                .lastName(generateString(7))
                .email(generateEmail(10))
                .phone(generatePhone(10))
                .address(generateString(10))
                .build();

        RequestBody requestBody = RequestBody.create(GSON.toJson(contactDtoLombok), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + ADD_CONTACT)
                .addHeader(AUTH, tokenDto.getToken())
                .post(requestBody)
                .build();

        try (Response response = OK_HTTP_CLIENT.newCall(request).execute())
        {
            ResponseMessageDto responseMessageDto = GSON.fromJson(response.body().string(), ResponseMessageDto.class);

            if(response.isSuccessful())
            {
                System.out.println("Contact was added: " + responseMessageDto.getMessage());
                softAssert.assertEquals(response.code(),200);
                softAssert.assertTrue(responseMessageDto.getMessage().contains("Contact was added!"));
                softAssert.assertAll();
            }

            else
            {
                Assert.fail("Contact wasn't added: " + response.code());
            }
        }

        catch (IOException e)
        {
            Assert.fail("Created exception: ");
        }
    }
}
