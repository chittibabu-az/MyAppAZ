package com.mycompany.geolocation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author techmachine
 */
final class GeoLocation {
    
    /**
     * this is the private constructor.
     */
    private GeoLocation() {
        
    }
    /**
     * GGGGGGGGGGGGGGGGGGGGGGGGGGG.
     * Find address execute method. 
     * @param arg is console argument.
     * @throws IOException is exception.
     */
    public static void main(final String[] arg)throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please Enter your IP ");
        System.out.print("http://freegeoip.net/xml/:");
        String ipValue = in.readLine();
        System.out.println("Please wait Engine searching location for your given IP.....");
        GeoLocator geoLocator = new GeoLocatorImpl();
        getAddress(geoLocator.locate(ipValue));
        System.out.println("Location search is completed");
    }
    
    /**
     * Show address based on IP address.
     * 
     * @param location object.
     */
    public static void getAddress(final Location location) {           
        System.out.println(location.getIpValue());
        System.out.println(location.getCityName());
        System.out.println(location.getRegionCode());
        System.out.println(location.getRegionName());
        System.out.println(location.getCountryName());            
        System.out.println(location.getCountryCode());                        
        System.out.println(location.getZipCode());
        System.out.println(location.getTimeZone());            
        System.out.println(location.getLatitudeValue());
        System.out.println(location.getLongitudeValue());
        System.out.println(location.getMetroCode());
    }
}
