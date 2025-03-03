package services;

import io.restassured.specification.RequestSpecification;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class APIHeader {

    public static RequestSpecification setHeaders(){
        return given()
                .header("content-type","application/json")
                .header("accept","application/json");
    }

    public static RequestSpecification setHeaders(Map<String, String> headers){
        return setHeaders()
                .headers(headers);
    }
}
