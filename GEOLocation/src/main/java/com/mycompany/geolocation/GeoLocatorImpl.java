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
 *
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
     * Global url Variable.
     */
    private String url = "http://freegeoip.net/xml/";

    /**
     * Global ip variable.
     */
    private String ip;

    /**
     * Global Object for Listing Address.
     */
    private Location locationList = new Location();

    /**
     * Input Stream Object.
     */
    private InputStream inputStream;

    /**
     * Assign immutable value in URL in constructor.
     */
    public GeoLocatorImpl() {
        this.url = "http://freegeoip.net/xml/";
        this.ip = "4.2.2.2";
    }

    /**
     * Assigning mutable value in URL in constructor.
     *
     * @param urlAddress as string.
     * @param ipAddress as string.
     */
    public GeoLocatorImpl(final String urlAddress, final String ipAddress) {
        this.url = urlAddress;
        this.ip = ipAddress;
    }

    /**
     * Validate IP address.
     *
     * @param ipAddress is a params.
     * @return location object.
     * @throws IOException as message.
     */
    @Override
    public Location locate(final String ipAddress) throws IOException {
        Location location = null;

        //Validate IP Address.
        if (!isValidIP(ipAddress)) {
            try {
                throw new InvalidIPException("In valid IP Address"); // I'm throwing user defined custom exception above
            } catch (InvalidIPException exp) {
                System.err.println("This is customized exception block.");
                System.err.println(exp);
            }
        }

        //Validate URL.
        if (!isValidURL()) {
            throw new IOException("In Valid URL.");
        }

        try {
            this.ip = ipAddress;
            //Get URL Connection.        
            if (!getURLConnection()) {
                throw new IOException("URL Connection Fail.");
            }
            location = locationList;
            return location;
        } catch (IOException e) {
            System.err.println(e);
            return location;
        }
    }

    /**
     * Validate ip address.
     *
     * @param ipAddress is string.
     * @return boolean value.
     */
    public boolean isValidIP(final String ipAddress) {
        // Compile the IP Pattern.
        pattern = Pattern.compile(IPADDRESS_PATTERN);
        // matching the values.
        matcher = pattern.matcher(ipAddress);
        return matcher.matches();
    }

    /**
     * Validate URL.
     *
     * @return boolean value.
     */
    public boolean isValidURL() {
        boolean returnValue = true;
        if (!url.matches(URL_PATTERN)) {
            returnValue = false;
        }
        return returnValue;
    }

    /**
     * Get or Open URL Connection.
     *
     * @return URLConnection Instance if it is open.
     */
    public boolean getURLConnection() {
        String host = "182.73.212.174";
        String port = "8080";
        System.out.println("Using proxy: " + host + ":" + port);
        System.setProperty("http.proxyHost", host);
        System.setProperty("http.proxyPort", port);
        System.setProperty("http.nonProxyHosts", "localhost|127.0.0.1");
        URLConnection conn;
        try {
            String connURL = url.concat(ip);
            URL urlInstance = new URL(connURL);//URL object.
            conn = urlInstance.openConnection();
            inputStream = conn.getInputStream();
            return true;
        } catch (IOException e) {
            System.err.println(e);
            return false;
        }
    }

    /**
     * Declare event reader object.
     */
    private XMLEventReader eventReader;

    /**
     * Description of Data parsing.
     *
     * @return location object.
     */
    public boolean xmlParsing() {
        if (!isEventReader()) {
            return false;
        }
        try {
            while (eventReader.hasNext()) {
                //XML event reads next event.
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    //Values loading into DB or List.
                    locationList = loadValue(event, eventReader);
                }
            }
            return true;
        } catch (XMLStreamException e) {
            System.err.println(e);
            return false;
        }

    }

    /**
     * Assign data with the help of event reader.
     *
     * @return boolean value.
     */
    public boolean isEventReader() {
        try {
            //XML Input Factory object.
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            //XML Event reader object.
            eventReader = inputFactory.createXMLEventReader(inputStream);
            return eventReader.hasNext();
        } catch (XMLStreamException e) {
            System.err.println(e);
            return false;
        }

    }

    /**
     * Get XML character.
     *
     * @param event as params.
     * @return as string.
     */
    public String getCharacter(final XMLEvent event) {
        if (event.isCharacters()) {
            return event.asCharacters().getData();
        } else {
            return "";
        }
    }

    /**
     * Load value into DB or List.
     *
     * @param eventParams of XML.
     * @param eventReaderParams of XML.
     * @return location object.
     * @throws XMLStreamException when is found.
     */
    public Location loadValue(final XMLEvent eventParams, final XMLEventReader eventReaderParams) throws XMLStreamException {
        StartElement startElement = eventParams.asStartElement();
        XMLEvent event = eventReaderParams.nextEvent();
        switch (startElement.getName().getLocalPart()) {
            case TAG_IP:
                locationList.setIpValue(getCharacter(event));
                break;
            case TAG_REGION_CODE:
                locationList.setRegionCode(getCharacter(event));
                break;
            case TAG_REGION_NAME:
                locationList.setRegionName(getCharacter(event));
                break;
            case TAG_CITY:
                locationList.setCityName(getCharacter(event));
                break;
            case TAG_ZIP_CODE:
                locationList.setZipCode(getCharacter(event));
                break;
            case TAG_COUNTRY_CODE:
                locationList.setCountryCode(getCharacter(event));
                break;
            case TAG_COUNTRY_NAME:
                locationList.setCountryName(getCharacter(event));
                break;
            case TAG_TIME_ZONE:
                locationList.setTimeZone(getCharacter(event));
                break;
            case TAG_LATITUDE:
                locationList.setLatitudeValue(getCharacter(event));
                break;
            case TAG_LONGITUDE:
                locationList.setLongitudeValue(getCharacter(event));
                break;
            case TAG_METRO_CODE:
                locationList.setMetroCode(getCharacter(event));
                break;
            default:

                break;
        }
        return locationList;
    }
}
