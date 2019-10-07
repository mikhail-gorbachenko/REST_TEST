package com.epam.interview;

import com.epam.interview.objects.User;
import com.epam.interview.objects.UserBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

public class RestTest {

    @BeforeMethod
    public void preLaunch(){
        baseURI = "https://reqres.in/api";
    }

    @Test
    public void postUser() {
        User user2 = new UserBuilder()
                .setFirstName("Airon")
                .setLastName("Bluth")
                .setAvatar("fff.ru")
                .setEmail("dff@kkd.ty")
                .build();

        User user3 = given().contentType("application/JSON").
                body(user2).
                when().post("/users").as(User.class);

        boolean equality = user2.compareSentReturnBody(user3);
        Assert.assertTrue(equality, "Users info not equals");
    }

    @Test
    public void dumbSingleUserTest() {
        expect().
                statusCode(200).
                body(
                        "data.id", notNullValue(),
                        "data.email", notNullValue(),
                        "data.first_name", notNullValue(),
                        "data.last_name", notNullValue(),
                        "data.avatar", notNullValue()).log().all().
                when().get("/users/2");
    }

    @Test
    public void getMultipleUsersTest() {
        RequestSpecification request = given();
        Response response = request.get("/users?page=2");
        User[] users = response.jsonPath().getObject("data", User[].class);
        for (User user : users) {
           Assert.assertTrue(user.selfIntegrityCheck(), "user has empty fields " + user.toString());
        }
    }


}
