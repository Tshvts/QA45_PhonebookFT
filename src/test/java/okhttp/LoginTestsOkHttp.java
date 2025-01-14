package okhttp;

import dto.ErrorMessageDto;
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
import static utils.RandomUtils.generateEmail;

public class LoginTestsOkHttp implements BaseApi
{
    SoftAssert softAssert = new SoftAssert();
    UserDtoLombok userDtoLombok;
    String email;
    String password;
    TokenDto tokenDto;

    @BeforeClass
    public void registration()
    {
        email = generateEmail(10);
        password = "Password123!";

        userDtoLombok = UserDtoLombok.builder()
                .username(email)
                .password(password)
                .build();

        RequestBody requestBody = RequestBody.create(GSON.toJson(userDtoLombok), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + REGISTRATION)
                .post(requestBody)
                .build();
        try
        {
            Response response = OK_HTTP_CLIENT.newCall(request).execute();

            if(response.isSuccessful())
            {
                System.out.println("Registration is successful" + this.userDtoLombok.toString());
            }

            else
            {
                System.out.println("Registration is failed" + response.code());
            }
        }

        catch (IOException e)
        {
            System.out.println("Created exception");
            throw new RuntimeException(e);
        }
    }

    @Test
    public void loginPositiveTest()
    {
        RequestBody requestBody = RequestBody.create(GSON.toJson(userDtoLombok), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + LOGIN)
                .post(requestBody)
                .build();

        try(Response response = OK_HTTP_CLIENT.newCall(request).execute())
        {
            if(response.isSuccessful())
            {
                System.out.println("Login is successful" + userDtoLombok.toString());
                tokenDto = GSON.fromJson(response.body().string(),TokenDto.class);
                System.out.println(tokenDto);
                softAssert.assertFalse(tokenDto.getToken().isBlank());
                softAssert.assertEquals(response.code(),200);
                softAssert.assertAll();
            }

            else
            {
                Assert.fail("Login is failed" + response.code());
            }
        }

        catch (IOException e)
        {
            Assert.fail("Created exception");
        }
    }

    @Test
    public void loginNegativeTest_WrongPassword_401()
    {
        userDtoLombok.setPassword("12");
        RequestBody requestBody = RequestBody.create(GSON.toJson(userDtoLombok), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + LOGIN)
                .post(requestBody)
                .build();

        try(Response response = OK_HTTP_CLIENT.newCall(request).execute())
        {
            if(!response.isSuccessful())
            {
                ErrorMessageDto errorMessageDto = GSON.fromJson(response.body().string(),ErrorMessageDto.class);
                System.out.println(errorMessageDto);
                softAssert.assertEquals(response.code(),401);
                softAssert.assertTrue(errorMessageDto.getMessage().toString().contains("Login or Password incorrect"));
                softAssert.assertTrue(errorMessageDto.getError().contains("Unauthorized"));
                softAssert.assertAll();
            }

            else
            {
                Assert.fail("Test is failed" + response.code());
            }
        }

        catch (IOException e)
        {
            Assert.fail("Created exception");
        }
    }
}
