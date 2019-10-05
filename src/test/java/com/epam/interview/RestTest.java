package com.epam.interview;

import com.epam.interview.objects.User;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class RestTest {

    Response response;
    User[] list;

    @Test
    public void notNullFieldUsers() {
        list = when().
                get("https://reqres.in/api/users?page=2").as(User[].class);

        for (User user: list) {
            System.out.println(user.selfIntegrityCheck());
        }


    }
}
