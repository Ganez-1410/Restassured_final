package utils;

import models.reqres.Users;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Utils {
    private static Properties properties;

    public List<Users> readCsv(String filePath) throws IOException {
        List<Users> users = new ArrayList<>();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        String data;
        bufferedReader.readLine();
        while((data = bufferedReader.readLine()) != null){
            String[] row = data.split(",");
            Users users1 = new Users(row[0],row[1]);
            users.add(users1);
        }
        return users;
    }

    static {
        try{
            properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/app.properties");
            properties.load(fileInputStream);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getPropertyData(String key){
        return properties.getProperty(key);
    }
}
