package com.izaanschool;

import base.Base;
import io.restassured.response.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.rules.TestName;

import static io.restassured.RestAssured.given;

public class IzaanSchoolAPITest {
    private static final Logger logger = LogManager.getLogger(GetWeatherDataTest.class);
    @Rule
    public TestName name = new TestName();

    @Test
    public void submitStatusTest() {
        String requestBody = "{\n" +             // this is how JSON key value for Body is convenient to put
                "  \"name\": \"John\",\n" +      // local variable is a variable that is declared inside a method or block of code.
                "  \"age\": 10 \n" +            // . It can only be accessed within that method or block of code
                "  }";

        String url = "https://5x9m5ed0tj.execute-api.us-east-1.amazonaws.com/test/submit";
        String authorization = "Bearer"+ Base.getIzaanSchoolToken();
        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", authorization)
                .and()
                .body(requestBody)
                .when()
                .post(url)
                .then()
                .extract().response();
        String message = response.jsonPath() .getString("message");
        logger.info(message);
        Assert.assertEquals(401, response.statusCode());
    }

}
