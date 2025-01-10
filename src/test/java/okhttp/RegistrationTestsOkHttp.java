package okhttp;

import dto.UserDtoLombok;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseApi;

import java.io.IOException;

import static utils.RandomUtils.*;

public class RegistrationTestsOkHttp implements BaseApi
{
    @Test
    public void registrationPositiveTestAPI()
    {
        UserDtoLombok user = UserDtoLombok.builder()
                .username(generateEmail(10))
                .password("Password123!")
                .build();

        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + REGISTRATION)
                .post(requestBody)
                .build();
        try {
            Response response = OK_HTTP_CLIENT.newCall(request).execute();
            Assert.assertEquals(response.code(),200);
        } catch (IOException e) {
            Assert.fail("Created exception");
            throw new RuntimeException(e);
        }
    }

    @Test
    public void registrationNegativeTestAPI_BadRequest()
    {
        UserDtoLombok user = UserDtoLombok.builder()
                .username(generateString(13))
                .password("Password123!")
                .build();

        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + REGISTRATION)
                .post(requestBody)
                .build();
        try {
            Response response = OK_HTTP_CLIENT.newCall(request).execute();
            Assert.assertEquals(response.code(),400);
        } catch (IOException e) {
            Assert.fail("Created exception");
            throw new RuntimeException(e);
        }
    }


    @Test
    public void registrationNegativeTestAPI_DublicateUser()
    {
        UserDtoLombok user = UserDtoLombok.builder()
                .username(generateEmail(10))
                .password("Password123!")
                .build();

        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + REGISTRATION)
                .post(requestBody)
                .build();
        try {
            Response response = OK_HTTP_CLIENT.newCall(request).execute();
            if(response.isSuccessful())
            {
                Response response1 = OK_HTTP_CLIENT.newCall(request).execute();
                Assert.assertEquals(response1.code(),409);
            }
        } catch (IOException e) {
            Assert.fail("Created exception");
            throw new RuntimeException(e);
        }
    }
}
