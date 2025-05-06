package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ApiFacade {
    public String getAttributeValueFromJson(String urlString, String attributeName) 
            throws IOException, IllegalArgumentException {
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder content = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(content.toString());
            
            if (!jsonObject.containsKey(attributeName)) {
                throw new IllegalArgumentException("Attribute not found");
            }
            
            return jsonObject.get(attributeName).toString();
        } 
        catch (ParseException e) {
            throw new IllegalArgumentException("Invalid JSON response");
        }
        finally {
            con.disconnect();
        }
    }
}