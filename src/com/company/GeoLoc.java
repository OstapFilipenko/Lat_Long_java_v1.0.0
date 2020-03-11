package com.company;

/**
 * class GeoLoc
 * @author Ostap Filipenko
 */
public class GeoLoc {
    Double lat;
    Double lon;

    /**
     * it return the latitude, it should be in the negative range
     * @return the Latitude
     */
    public Double getLat() {
        return lat;
    }

    /**
     * gets a String and converting this in a Double variable
     * @param lat
     */
    public void setLat(String lat) {
        this.lat = Double.parseDouble(lat);
    }

    /**
     * is returning the Longtitude, it should not be in a negative range
     * @return the Longtitude
     */
    public Double getLon() {
        return lon;
    }

    /**
     * gets a String and converts this var in a Double
     * @param lon
     */
    public void setLon(String lon) {
        this.lon = Double.parseDouble(lon);
    }

    /**
     * generates a human well readable output
     * @return the Latitude and the Longtitude as a String
     */
    public String toString(){
        return "Lat: " + getLat() + " | Lon: " + getLon();
    }
}
