import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import constants.Endpoints;
import io.restassured.http.Method;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.reqres.UsersResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import services.APIServices;

import java.util.*;

public class ReqresAgain{

    private static final String name = "Ganesh";
    private static final String job = "QA";

    @BeforeMethod(alwaysRun = true)
    public void setBaseUri(){
        APIServices.setRestAssuredRequestResource("REQRES_BASEURI");
    }

    public Map<String, Object> getUsers(){
        String getUsersEndpoint = Endpoints.USER;
        int totalPages = 1, page = 1, totalData = 0;
        Map<String, Object> usersList = new LinkedHashMap<>();
        Map<String, String> queryParam = new LinkedHashMap<>();

        while(page <= totalPages) {

            queryParam.put("page",String.valueOf(page));
            Response response = APIServices.makeRequest(Method.GET, getUsersEndpoint, queryParam, null, null);
            JsonPath jsonPath = response.jsonPath();

            totalPages = jsonPath.getInt("total_pages");
            totalData += jsonPath.getInt("per_page");
            page++;

            List<Map<String, Object>> data = response.jsonPath().getList("data");
            for(Map<String, Object> user: data)
                usersList.putAll(user);
            System.out.println(data);
        }
        System.out.println(usersList);
        Assert.assertEquals(totalData, usersList.size(),"Incorrect total users count");

        return usersList;
    }

    @Test
    public void createUsers() throws JsonProcessingException {
        String createUsersEndpoint = Endpoints.USER;

        Map<String, String> payload = new HashMap<>();
        payload.put("name",name);
        payload.put("job", job);

        Response response = APIServices.makeRequest(Method.POST, createUsersEndpoint, null, null, payload);
        UsersResponse usersResponse = response.as(UsersResponse.class);
        UsersResponse usersResponse1 = new ObjectMapper().readValue(response.asString(), UsersResponse.class);


        Map<String, Object> usersList = getUsers();
        System.out.println(usersList);

        for(Map.Entry<String, Object> entry: usersList.entrySet()){
            System.out.println(entry.getValue());
        }

    }
}
