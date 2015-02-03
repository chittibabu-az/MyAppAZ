package com.mycompany.geolocation;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import java.io.IOException;
import javax.xml.stream.XMLStreamException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author techmachine
 */
public class GeoLocationTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8081);

    @Before
    public void init() {
        stubFor(get(urlEqualTo("/hoge.txt")).willReturn(aResponse().withStatus(200).withHeader("Content-Type", "application/json").withBody("hoge")));
        stubFor(get(urlEqualTo("/500.txt")).willReturn(aResponse().withStatus(500).withHeader("Content-Type", "text/plain").withBody("hoge")));
        stubFor(get(urlEqualTo("/503.txt")).willReturn(aResponse().withStatus(503).withHeader("Content-Type", "text/plain").withBody("hoge")));

        stubFor(get(urlEqualTo("/")).willReturn(aResponse().withStatus(200).withHeader("Content-Type", "application/xml").withBodyFile("test.xml")));
    }

    @Test
    public void isLocate() throws IOException {
        GeoLocatorImpl geoLocator = new GeoLocatorImpl("http://localhost:8080/", "");
        Location location = new Location();
        location = geoLocator.locate("");
        Assert.assertEquals(geoLocator.locate(""), location);
    }

    @Test
    public void isNotLocate() throws IOException {
        GeoLocatorImpl geoLocator = new GeoLocatorImpl("http://localhost:8080/", "");
        Assert.assertEquals(geoLocator.locate("4.2.2.2"), null);
    }

    @Test
    public void isValidIP() {
        GeoLocatorImpl geoLocator = new GeoLocatorImpl();
        Assert.assertEquals(geoLocator.isValidIP("192.168.1.1"), true);
    }

    @Test
    public void isInvalidIP() {
        GeoLocatorImpl geoLocator = new GeoLocatorImpl();
        Assert.assertEquals(geoLocator.isValidIP("192.168"), false);
    }

    @Test
    public void isValidURL() {
        GeoLocatorImpl geoLocator = new GeoLocatorImpl("http://freegeoip.net/xml/", "4.2.2.2");
        Assert.assertEquals(geoLocator.isValidURL(), true);
    }

    @Test
    public void isInvalidURL() {
        GeoLocatorImpl geoLocator = new GeoLocatorImpl("3453453http://freegeoip.net/xml/", "4.2.2.2");
        Assert.assertEquals(geoLocator.isValidURL(), false);
    }

    @Test
    public void isEventReader() throws XMLStreamException {
        GeoLocatorImpl geoLocator = new GeoLocatorImpl("http://localhost:8080/", "");
        Assert.assertEquals(geoLocator.isEventReader(), true);
    }

    @Test
    public void isNotEventReader() throws XMLStreamException {
        GeoLocatorImpl geoLocator = new GeoLocatorImpl("http://localhost:8080/", "4.2.2.2");
        Assert.assertEquals(geoLocator.isEventReader(), false);
    }

    @Test
    public void xmlParsingOk() throws XMLStreamException {
        GeoLocatorImpl geoLocator = new GeoLocatorImpl("http://freegeoip.net/xml/", "4.2.2.2");
        geoLocator.isEventReader();
        Assert.assertEquals(geoLocator.xmlParsing(), true);
    }

    @Test
    public void xmlParsingNotOk() throws XMLStreamException {
        GeoLocatorImpl geoLocator = new GeoLocatorImpl("http://localhost:8080/", "4.2.2.2");
        Assert.assertEquals(geoLocator.xmlParsing(), false);
    }

    @Test
    public void testConnection() throws IOException {
        GeoLocatorImpl geoLocator = new GeoLocatorImpl("http://freegeoip.net/xml/", "4.2.2.2");
        Assert.assertEquals(geoLocator.getURLConnection(), true);
    }
}
