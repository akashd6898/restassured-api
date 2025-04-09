package utils;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;

public class JSONReader {

    public static String getTestData(String key) throws IOException, ParseException {
        String test_Data;
        return test_Data = (String) getJSONData().get(key);
    }

    private static JSONObject getJSONData() throws IOException, ParseException {
        File file = new File(System.getProperty("user.dir")+"\\testData\\testdata.json");
        String jsontoString = FileUtils.readFileToString(file);
        Object obj = new JSONParser().parse(jsontoString);
        return (JSONObject) obj;
    }
}
