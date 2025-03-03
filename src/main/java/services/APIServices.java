package services;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.Utils;

import java.util.Map;

public class APIServices {

    public static void setRestAssuredRequestResource(String key) {
        RestAssured.baseURI = Utils.getPropertyData(key);
        RestAssured.useRelaxedHTTPSValidation();
    }

    public static Response get(String endpoint){
        Response response = null;
        try{
            response = APIHeader.setHeaders()
                    .given().when().log().all()
                    .get(endpoint)
                    .then().log().all().extract().response();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    public static Response get(String endpoint, Map<String, String> params){
        Response response = null;
        try{
            response = APIHeader.setHeaders()
                    .given().when().log().all()
                    .queryParams(params)
                    .get(endpoint)
                    .then().log().all().extract().response();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    public static Response get(String endpoint, Map<String, String> params, Map<String, String> headers){
        Response response = null;
        try{
            RequestSpecification requestSpecification = (headers != null) ? APIHeader.setHeaders(headers) : APIHeader.setHeaders();
            if(params != null)
                 requestSpecification.params(params);

            response = requestSpecification.given()
                    .when().log().all()
                    .get(endpoint)
                    .then().log().all().extract().response();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    public static Response post(String endpoint, String body){
        Response response = null;

        try{
            response = APIHeader.setHeaders()
                    .given().when().log().all()
                    .body(body)
                    .post(endpoint)
                    .then().log().all().extract().response();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    public static Response post(String endpoint, String body, Map<String, String> params){
        Response response = null;
        try{
            response = APIHeader.setHeaders()
                    .given().when().log().all()
                    .queryParams(params)
                    .body(body)
                    .post(endpoint)
                    .then().log().all().extract().response();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    public static Response post(String endpoint, Map<String, String> params,String payload, Map<String, String> headers){
        Response response = null;
        try{
            RequestSpecification requestSpecification = (headers != null) ? APIHeader.setHeaders(headers) : APIHeader.setHeaders();
            if(params != null)
                requestSpecification.params(params);

            response = requestSpecification.given()
                    .when().log().all()
                    .body(payload)
                    .post(endpoint)
                    .then().log().all().extract().response();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    public static Response put(String endpoint, String body){
        Response response = null;
        try{
            response = APIHeader.setHeaders()
                    .given().when().log().all()
                    .body(body)
                    .put(endpoint)
                    .then().log().all().extract().response();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    public static Response put(String endpoint, String body, Map<String, String> params){
        Response response = null;
        try{
            response = APIHeader.setHeaders()
                    .given().when().log().all()
                    .queryParams(params)
                    .body(body)
                    .put(endpoint)
                    .then().log().all().extract().response();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    public static Response put(String endpoint, Map<String, String> params,String payload, Map<String, String> headers){
        Response response = null;
        try{
            RequestSpecification requestSpecification = (headers != null) ? APIHeader.setHeaders(headers) : APIHeader.setHeaders();
            if(params != null)
                requestSpecification.params(params);

            response = requestSpecification.given()
                    .when().log().all()
                    .body(payload)
                    .put(endpoint)
                    .then().log().all().extract().response();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    public static Response delete(String endpoint){
        Response response = null;
        try {
            response = APIHeader.setHeaders()
                    .given().when().log().all()
                    .delete(endpoint)
                    .then().log().all().extract().response();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    public static Response delete(String endpoint, Map<String, String> params){
        Response response = null;
        try {
            response = APIHeader.setHeaders()
                    .given().when().log().all()
                    .queryParams(params)
                    .delete(endpoint)
                    .then().log().all().extract().response();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    public static Response delete(String endpoint, Map<String, String> params, Map<String, String> headers){
        Response response = null;
        try{
            RequestSpecification requestSpecification = (headers != null) ? APIHeader.setHeaders(headers) : APIHeader.setHeaders();
            if(params != null)
                requestSpecification.params(params);

            response = requestSpecification.given()
                    .when().log().all()
                    .delete(endpoint)
                    .then().log().all().extract().response();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }
}
