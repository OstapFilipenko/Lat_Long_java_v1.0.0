package com.company;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * class Location
 * @author Ostap Filipenko
 *
 */

public class Location {

    /**
     * gets a Json object of an api
     * @param location
     * @return the Latitude and Longtitude af a city
     */
    public String getLocation(String location){
        System.out.println("City: " + location);
        try{
            String url = "https://eu1.locationiq.com/v1/search.php?key=458944a57fc7ce&q="+ location +"&format=json";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            BufferedReader in =new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONParser parser = new JSONParser();

            JSONArray array = (JSONArray)parser.parse(String.valueOf(response));

            GeoLoc g = new GeoLoc();
            g.setLat(((JSONObject)array.get(0)).get("lat").toString());
            g.setLon(((JSONObject)array.get(0)).get("lon").toString());

            return g.toString();
        }catch (Exception e){
            return "Something went wrong!";
        }

    }

    /**
     *
     * @return the City name of public ip
     * @throws IOException
     */
    public String getLocation() throws IOException {
        return getLocation(getCityName());
    }

    /**
     * gets the ip address by using a website http://bot.whatismyipaddress.com
     * @return the public ip address
     */
    private String getPublicIpAdress(){
        String systemipadress = "";
        try {
            URL url_name = new URL("http://bot.whatismyipaddress.com");

            BufferedReader sc = new BufferedReader(new InputStreamReader(url_name.openStream()));

            systemipadress = sc.readLine().trim();
        } catch (Exception e) {
            systemipadress = "Cannot Execute Properly";
        }
        return systemipadress;
    }

    /**
     * gets the city by the ip address, using the https://tools.keycdn.com webservice
     * @return the City name
     * @throws IOException
     */
    private String getCityName() throws IOException {
        String ip = getPublicIpAdress();
        String theCityFinderURL = "https://tools.keycdn.com/geo?host="+ip;

        Document websiteHTML = Jsoup.connect(theCityFinderURL).followRedirects(false).timeout(60000).get();

        String city = websiteHTML.body().select("#geoResult div dl  dd").get(0).text();

        return city;
    }


}

