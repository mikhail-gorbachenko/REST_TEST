package com.epam.interview;

import com.epam.interview.objects.User;
import com.epam.interview.objects.UserBuilder;
import com.google.gson.Gson;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
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
