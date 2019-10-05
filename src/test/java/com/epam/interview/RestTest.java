package com.epam.interview;

import com.epam.interview.objects.User;
import com.epam.interview.objects.UserBuilder;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static io.restassured.RestAssured.*;

public class RestTest {

    User user1 = new User(1, "george.bluth@reqres.in", "George", "Bluth", "https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg");
    User user2 = new UserBuilder()
            .setFirstName("Airon")
            .setLastName("Bluth")
            .setAvatar("fff.ru")
            .setEmail("dff@kkd.ty")
            .build();

    Response response;
    User[] list;

    @Test
    public void TestSingleUser() {
        User user2 = get("https://reqres.in/api/users/2").as(User.class);
        user2.selfIntegrityCheck();
        System.out.println(user2);
    }

    @Test
    public void getUserAgain(){
        System.out.println(get("https://reqres.in/api/users/2/data").asString());
    }


/*    @Test
    public void notNullFieldUsers() {
        list = when().
                get("https://reqres.in/api/users?page=2").as(User[].class);

        for (User user: list) {
            System.out.println(user.selfIntegrityCheck());
        }
}
*/

    @Test
    public void postUser() {
       User user3 = given().proxy(5555).log().all().contentType("text/JSON").
                body(user2).
                when().get("https://reqres.in/api/users").as(User.class);

       System.out.println(user3);
    }

    @Test
    public void dumbSingleUserTest(){
        expect().
                statusCode(200).
                body(
                        "id", notNullValue(),
                        "email", notNullValue(),
                        "first_name", notNullValue(),
                        "last_name", notNullValue(),
                        "avatar", notNullValue()).log().all().
                when().get("https://reqres.in/api/users/2");
    }


}
