package services;

import io.restassured.http.Headers;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
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

    public static <T> RequestSpecification setHeaders(T headers) throws IllegalAccessException {
        System.out.println("class: "+headers.getClass());

        if(headers instanceof Map<?,?>)
            return setHeaders()
                    .headers((Map<String, ?>) headers);

        else if(headers instanceof Headers)
            return setHeaders()
                    .headers((Headers) headers);

        throw new IllegalAccessException("");
    }
}
