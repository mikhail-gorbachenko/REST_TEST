package com.epam.interview;

import com.epam.interview.objects.User;
import com.epam.interview.objects.UserBuilder;
import com.google.gson.Gson;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import java.util.List;

import static io.restassured.RestAssured.*;

public class RestTest {

    User user2 = new UserBuilder()
            .setFirstName("Airon")
            .setLastName("Bluth")
            .setAvatar("fff.ru")
            .setEmail("dff@kkd.ty")
            .build();

    @Test
    public void notSoDumbTestSingleUser() {
        String json = get("https://reqres.in/api/users/2").asString();
        JsonPath jp = new JsonPath(json);
        //jp.setRootPath("data");
        //json = jp.get("data");
        json = jp.getString("data");
        User user = new Gson().fromJson(json, User.class );
        user.selfIntegrityCheck();
        System.out.println(user);
    }

    @Test
    public void postUser() {
       User user3 = given().log().all().contentType("application/JSON").
                body(user2).
                when().post("https://reqres.in/api/users").as(User.class);

       boolean equality = user2.compareSentReturnBody(user3);
        Assert.assertTrue(equality, "Users info not equals");
    }

    @Test
    public void dumbSingleUserTest(){
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
    public void dumbMultipleUsersTest(){
        String response = get("https://reqres.in/api/users?page=2").toString();
        JsonPath jp = new JsonPath(response);
        jp.setRootPath("data");

    }


}
