package okhttp;

import dto.*;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.BaseApi;

import java.io.IOException;

public class DeleteContactByIDTests implements BaseApi {
    UserDtoLombok userDtoLombok;
    SoftAssert softAssert = new SoftAssert();
    TokenDto tokenDto;
    ContactDtoLombok contactDtoLombok;
    ContactsDto contactsDto;

    @BeforeClass(alwaysRun = true)
    public void login() {
        userDtoLombok = UserDtoLombok.builder()
                .username("o3hfpb1dov@gmail.com")
                .password("Password123!")
                .build();

        RequestBody requestBody = RequestBody.create(GSON.toJson(userDtoLombok), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + LOGIN)
                .post(requestBody)
                .build();

        try (Response response = OK_HTTP_CLIENT.newCall(request).execute()) {
            if (response.isSuccessful()) {
                System.out.println("Login is successful + " + userDtoLombok.toString());
                tokenDto = GSON.fromJson(response.body().string(), TokenDto.class);
                System.out.println(tokenDto);
            } else {
                Assert.fail("Login is failed: " + response.code());
            }
        } catch (IOException e) {
            Assert.fail("Created exception: ");
        }
    }

    @Test
    public void deleteContactByIDPositiveTest() {
        String idFirstContact = "";

        Request request = new Request.Builder()
                .url(BASE_URL + ADD_CONTACT)
                .addHeader(AUTH, tokenDto.getToken())
                .get()
                .build();

        try (Response response = OK_HTTP_CLIENT.newCall(request).execute()) {
            if (response.isSuccessful()) {
                contactsDto = GSON.fromJson(response.body().string(), ContactsDto.class);
                for (ContactDtoLombok contacts : contactsDto.getContacts()) {
                    System.out.println(contacts.toString());
                }

                idFirstContact = contactsDto.getContacts()[0].getId();
                System.out.println("The first contact id==> " + idFirstContact);
            } else {
                System.out.println("Get isn't successful: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail("Created exception");
        }

        Request requestDelete = new Request.Builder()
                .url(BASE_URL + ADD_CONTACT + "/" + idFirstContact)
                .addHeader(AUTH, tokenDto.getToken())
                .delete()
                .build();
        try (Response response = OK_HTTP_CLIENT.newCall(requestDelete).execute()) {
            if (response.isSuccessful()) {
                System.out.println("Contact was deleted: " + contactsDto);
                softAssert.assertEquals(response.code(), 200);
                softAssert.assertAll();
            } else {
                System.out.println("Request isn't successful: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail("Created exception");
        }

    }

    @Test
    public void deleteContactByIDNegativeTest_400() {
        Request requestDelete = new Request.Builder()
                .url(BASE_URL + ADD_CONTACT + "/" + 3)
                .addHeader(AUTH, tokenDto.getToken())
                .delete()
                .build();
        try (Response response = OK_HTTP_CLIENT.newCall(requestDelete).execute()) {
            if (!response.isSuccessful()) {
                System.out.println("Contact wasn't deleted: " + response.code());
                softAssert.assertEquals(response.code(), 400);
                softAssert.assertAll();
            } else {
                System.out.println("Request is successful: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail("Created exception");
        }
    }

    @Test
    public void deleteContactByIDNegativeTest_401() {
        String idFirstContact = "";

        Request request = new Request.Builder()
                .url(BASE_URL + ADD_CONTACT)
                .addHeader(AUTH, tokenDto.getToken())
                .get()
                .build();

        try (Response response = OK_HTTP_CLIENT.newCall(request).execute()) {
            if (response.isSuccessful()) {
                contactsDto = GSON.fromJson(response.body().string(), ContactsDto.class);
                for (ContactDtoLombok contacts : contactsDto.getContacts()) {
                    System.out.println(contacts.toString());
                }

                idFirstContact = contactsDto.getContacts()[0].getId();
                System.out.println("The first contact id==> " + idFirstContact);
            } else {
                System.out.println("Get isn't successful: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail("Created exception");
        }

        Request requestDelete = new Request.Builder()
                .url(BASE_URL + ADD_CONTACT + "/" + idFirstContact)
                .addHeader(AUTH, tokenDto.getToken() + "d")
                .delete()
                .build();
        try (Response response = OK_HTTP_CLIENT.newCall(requestDelete).execute()) {
            if (!response.isSuccessful()) {
                System.out.println("Contact wasn't deleted: " + response.code());
                softAssert.assertEquals(response.code(), 401);
                softAssert.assertAll();
            } else {
                System.out.println("Request is successful: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail("Created exception");
        }
    }


    @Test
    public void deleteContactByIDNegativeTest_500() {
        Request requestDelete = new Request.Builder()
                .url(BASE_URL + ADD_CONTACT + "/")
                .addHeader(AUTH, tokenDto.getToken())
                .delete()
                .build();
        try (Response response = OK_HTTP_CLIENT.newCall(requestDelete).execute()) {
            if (!response.isSuccessful()) {
                System.out.println("Contact wasn't deleted: " + response.code());
                softAssert.assertEquals(response.code(), 500);
                softAssert.assertAll();
            } else {
                System.out.println("Request is successful: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail("Created exception");
        }
    }
}