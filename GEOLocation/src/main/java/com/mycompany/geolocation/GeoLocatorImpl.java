/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.geolocation;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;



/**
 * To Write description for methods of interface.
 * @author techmachine
 */
public class GeoLocatorImpl implements GeoLocator {
    /**
     * Object for set pattern of IP address.
     */
    private Pattern pattern;
    
    /**
     * IP matching object.
     */
    private Matcher matcher;
    
    /**
     * Initialize pattern of IP Address.
     */
    private static final String IPADDRESS_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
    
    /**
     * Initialize pattern of URL.
     */
    private static final String URL_PATTERN = "\\b(https?|ftp|file|ldap)://"
            + "[-A-Za-z0-9+&@#/%?=~_|!:,.;]"
            + "*[-A-Za-z0-9+&@#/%=~_|]";
    
    /**
     * IP tag pattern.
     */
    private static final String TAG_IP = "IP";
    
    /**
     * Region code tag pattern.
     */
    private static final String TAG_REGION_CODE = "RegionCode";
    
    /**
     * Region name tag pattern.
     */
    private static final String TAG_REGION_NAME = "RegionName";
    
    /**
     * city tag pattern.
     */
    private static final String TAG_CITY = "City";
    
    /**
     * Zip code tag pattern.
     */
    private static final String TAG_ZIP_CODE = "ZipCode";
    
    /**
     * Country Code tag pattern.
     */
    private static final String TAG_COUNTRY_CODE = "CountryCode";
    
    /**
     * Country Name tag pattern.
     */
    private static final String TAG_COUNTRY_NAME = "CountryName";
    
    /**
     * Time Zone tag pattern.
     */
    private static final String TAG_TIME_ZONE = "TimeZone";
    
    /**
     * Latitude tag pattern.
     */
    private static final String TAG_LATITUDE = "Latitude";
    
    /**
     * Longitude tag pattern.
     */
    private static final String TAG_LONGITUDE = "Longitude";
    
    /**
     * Metro Code tag pattern.
     */
    private static final String TAG_METRO_CODE = "MetroCode";    
    
    /**
     * Static URL String.
     */
    private static final String URL = "http://freegeoip.net/xml/";
    
    /**
     * Validate IP address.
     * 
     * @param ip is a params.
     * @return location object.
     * @throws IOException as message.
     */
    @Override
    public Location locate(final String ip) throws IOException { 
        Location location = null;
        //Validate IP Address.
        if (!isValidIP(ip)) {
            try {
                throw new InvalidIPException("In valid IP Address"); // I'm throwing user defined custom exception above
            } catch (InvalidIPException exp) {
                System.err.println("This is customized exception block.") ;
                System.err.println(exp) ;
            }
        }        
        
        String url = URL.concat(ip);
        
        //Validate URL.
        if (!isValidURL(url)) {
            throw new IOException("In Valid URL."); 
        }
            
        
        //Get URL Connection.        
        if (getURLConnection(url) == null) {
            throw new IOException("URL Connection Fail."); 
//            return location;
        }
        try {
            return xmlParsing(getURLConnection(url).getInputStream());
        } catch (IOException | XMLStreamException e) {
            System.err.println(e);
            return location;
        }
    }  
    
    /**
     * Validate ip address.
     * 
     * @param ip is string.
     * @return boolean value.
     */
    public boolean isValidIP(final String ip) {
        // Compile the IP Pattern.
        pattern = Pattern.compile(IPADDRESS_PATTERN);
        // matching the values.
        matcher = pattern.matcher(ip);
        return matcher.matches();  
    }
    
    /**
     * Validate URL.
     * 
     * @param url as String.
     * @return boolean value.
     */
    public boolean isValidURL(String url) {
        boolean returnValue = true;
        if (!url.matches(URL_PATTERN)) {
            returnValue = false;
        }
        //TODO: server not found exception.
        
        //TODO: 404 Not Fond Exception.
        //TODO: 500 Error.
        //TODO: 503 Error.
        return returnValue;
    }
   
    /**
     * Get or Open URL Connection.
     * 
     * @param url is a String.
     * @return URLConnection Instance if it is open.
     */
    public URLConnection getURLConnection(final String url) {
        URLConnection conn; 
        try {            
            URL urlInstance = new URL(url);//URL object.
            conn = urlInstance.openConnection();
            return conn;   
        } catch (IOException e) {
            return null;   
        }
    }
    
    /**
     * Description of Data parsing.
     * 
     * @param inputStream is xml data stream.
     * @return location object.
     * @throws XMLStreamException is DATA parsing Exception.
     */
    public Location xmlParsing(final InputStream inputStream) throws XMLStreamException {  
        //Location object.
        Location location = null;
        //XML Input Factory object.
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        //XML Event reader object.
        XMLEventReader eventReader = inputFactory.createXMLEventReader(inputStream);
        while (eventReader.hasNext()) {
            //XML event reads next event.
            XMLEvent event = eventReader.nextEvent();
            if (event.isStartElement()) {
                //Values loading into DB or List.
                location = loadValue(event,eventReader);                  
            }
        }
        
        return location;
    } 
    
    /**
     * Global Object for Listing Address.
     */
    private final Location locationList = new Location();
    
    /**
     * Load value into DB or List.
     * 
     * @param event of XML.
     * @param eventReader of XML.
     * @return location object.
     * @throws XMLStreamException 
     */    
    public Location loadValue(XMLEvent event,final XMLEventReader eventReader) throws XMLStreamException {                
        StartElement startElement = event.asStartElement();   
        event = eventReader.nextEvent();
        switch(startElement.getName().getLocalPart()) {
            case TAG_IP :
                locationList.setIpValue(event.isCharacters() ? event.asCharacters().getData() : "");
                break;
            case TAG_REGION_CODE :
                locationList.setRegionCode(event.isCharacters() ? event.asCharacters().getData() : "");
                break;
            case TAG_REGION_NAME :
                locationList.setRegionName(event.isCharacters() ? event.asCharacters().getData() : "");
                break;
            case TAG_CITY :
                locationList.setCityName(event.isCharacters() ? event.asCharacters().getData() : "");
                break;
            case TAG_ZIP_CODE :
                locationList.setZipCode(event.isCharacters() ? event.asCharacters().getData() : "");
                break;
            case TAG_COUNTRY_CODE :
                locationList.setCountryCode(event.isCharacters() ? event.asCharacters().getData() : "");
                break;
            case TAG_COUNTRY_NAME :
                locationList.setCountryName(event.isCharacters() ? event.asCharacters().getData() : "");
                break;
            case TAG_TIME_ZONE : 
                locationList.setTimeZone(event.isCharacters() ? event.asCharacters().getData() : "");
                break;
            case TAG_LATITUDE :
                locationList.setLatitudeValue(event.isCharacters() ? event.asCharacters().getData() : "");
                break;
            case TAG_LONGITUDE :
                locationList.setLongitudeValue(event.isCharacters() ? event.asCharacters().getData() : "");
                break;
            case TAG_METRO_CODE :
                locationList.setMetroCode(event.isCharacters() ? event.asCharacters().getData() : "");                
                break;
            default :
                
                break;
       }
       return locationList;
    }
}
