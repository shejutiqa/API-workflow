package base;

import com.izaanschool.GetWeatherDataTest;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class Base {
    private static  Logger logger = LogManager.getLogger(Base.class.getName());
    public static final  String hostname= "https://api.openweathermap.org";
    public static String buildURL(String resource, String queryParameter){
        String url = hostname + resource + queryParameter;
        logger.info("End Point :" + resource);
        return url;
    }
    public static String getIzaanSchoolToken() {
        String url = "https://izaan-test.auth.us-east-1.amazoncognito.com/oauth2/token";
        Response response = given()
                .header("Content-type", "application/x-www-form-urlencoded")
                .header("Authorization", "Basic MXU1aW80dmE5c3I0NW43OWZjZWcyZGFtamY6MXFia3RodnA3bGJjN2FhdnVoaG1mZzhmMmNyZWtvcjloMmg3YWJ1Mm9ydTFubHBqNzFmZQ==")
                .and()
                .formParam("scope", "izaan_test/post_info")
                .formParam("grant_type", "client_credentials")
                .when()
                .post(url)
                .then()
                .extract().response();
        String token = response.jsonPath().getString("access_token");
        logger.info(token);
       return token;
    }

}

/*
*  curl --location 'https://5x9m5ed0tj.execute-api.us-east-1.amazonaws.com/test/submit' \
--header 'Authorization: Bearer eyJraWQiOiJtNlhhM3FkczlxY2o2ODZYOVBRb0JqTEJSQU5yMzBPZ21ZQzdvdThZc1N3PSIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiIxdTVpbzR2YTlzcjQ1bjc5ZmNlZzJkYW1qZiIsInRva2VuX3VzZSI6ImFjY2VzcyIsInNjb3BlIjoiaXphYW5fdGVzdFwvcG9zdF9pbmZvIiwiYXV0aF90aW1lIjoxNjczNzIzMTI4LCJpc3MiOiJodHRwczpcL1wvY29nbml0by1pZHAudXMtZWFzdC0xLmFtYXpvbmF3cy5jb21cL3VzLWVhc3QtMV9ZN3k4cTVDMVoiLCJleHAiOjE2NzM3MjY3MjgsImlhdCI6MTY3MzcyMzEyOCwidmVyc2lvbiI6MiwianRpIjoiNzQ2NWYyMGItM2VlZS00ODgzLThjMTUtM2YyM2ZhYjI2MTc4IiwiY2xpZW50X2lkIjoiMXU1aW80dmE5c3I0NW43OWZjZWcyZGFtamYifQ.HX8ntkB6yh9EHNyWRIB-UuKkza9T-9w3GxseQtxGbkC7q1MD2sum-0IZGgRiLSiBZy8Ays2LTPuGZzqXueK4besAhA_WwIT1rWOP07LexDkI5YBuDQew3yI-gGavSPQIldPGE8jlm_vSs-8im298fXvgIdHD0I-BWGq1tDb-Lwoi0GEeyPjXRxWfrLaAPGnW_tNJNRkPYjJJ6uUvxFsw3DpEgUYnzaDz5ekM6hOWgdaCH_hA6inxyDFAJFwdUgL5VawGtTHzLvFaVYF40AefDMQLWkp1m0-2keFNseZFCBzlzBsdhGQca9Y7vI4ZbJE4CqB3v1aoAxSzT8hCFEe6cw' \
--header 'Content-Type: application/json' \
--data '{
  "name" : "John",
  "age" : 10
}'
*
* */
