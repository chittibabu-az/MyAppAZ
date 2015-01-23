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
import javax.xml.stream.XMLStreamException;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author techmachine
 */
public class GeoLocationTest {     
    static GeoLocatorImpl geoLocator = new GeoLocatorImpl(); 
    @Test
    public void isValidIP()
    {
        Assert.assertEquals(geoLocator.isValidIP("192.168.1.1"), true);
    }
    
    @Test
    public void isInValidIP()
    {
        Assert.assertEquals(geoLocator.isValidIP("192.168"), false);
    }
    
//    @Test
//    public void testConnection()throws IOException
//    {
//        URL urlInstance = new URL("http://freegeoip.net/xml/192.168.1.1");//URL object.            
//        URLConnection conn = urlInstance.openConnection(); //URL connection.
//        Assert.assertEquals(geoLocator.getURLConnection("http://freegeoip.net/xml/192.168.1.1"), conn);
//    }
    @Test
    public void xmlParsing()throws IOException, XMLStreamException
    {        
        Location location=new Location();
        InputStream ins=geoLocator.getURLConnection("http://freegeoip.net/xml/4.2.2.2").getInputStream();
        Assert.assertEquals(geoLocator.xmlParsing(ins), null);
    }
    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
//    @Before
//    public void setUp() {
//    }
//    
//    @After
//    public void tearDown() {
//    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
