package com.epam.interview;

import com.epam.interview.objects.User;
import com.epam.interview.objects.UserBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

public class RestTest {

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
                when().post("https://reqres.in/api/users").as(User.class);

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
                when().get("https://reqres.in/api/users/2");
    }

    @Test
    public void getMultipleUsersTest() {
        baseURI = "https://reqres.in/api/users?page=2"; //можно вынести в беформетод
        RequestSpecification request = given();
        Response response = request.get();
        User[] users = response.jsonPath().getObject("data", User[].class);
        for (User user : users) {
           Assert.assertTrue(user.selfIntegrityCheck(), "user has empty fields " + user.toString());
        }
    }


}
