
public class GeoLocatorImpl implements GeoLocator
        
    Interface helps to find the Geo Address Location based on IP Address. The functionality done in GeoLocationImpl.java.



locate
 
    Location locate(final String ip)  throws IOException; 
        This is the method which one covering all the functionality. Fetch the Address details when pass IP address as parameter through this method. 
    
    Parameters :
        ip - IP address.

    Returns :
        Object Location Instance that contain the XML data.

    Throws :
        InvalidIPException - this exception will trigger when given IP string is wrong.
        IOException -   this exception will trigger when InputStream found exception.

isValidIP        

    public boolean isValidIP(final String ip); 
        Here validate ip address before open the URL connection.

    Parameters :
        ip - IP address.
    
    Returns : 
        true/false - boolean values.

    Throws :
        InvalidIPException - this exception will trigger when given IP string is wrong.

isValidURL

    public boolean isValidURL(String url);
        Here validate url before open the URL connection.

    Parameters : 
        url - url address as string.

    Retruns :
        true/false - boolean values.

getURLConnection

    public URLConnection getURLConnection(final String url);
        Return the connection object based valid URL.

    Parameters :
        url - url address as string.

    Returns : 
        conn - URLConnection object.

    Throws :
        IOException.
xmlParsing

    public Location xmlParsing(final InputStream inputStream) throws XMLStreamException;
        Fetch XML data and parsing based on the http response.

    Parameters : 
        inputStream-it contain response from the URLConnection.

    Returns :
        location-Location entity object.

    Throws :
        XMLStreamException

loadValue

    public Location loadValue(XMLEvent event,final XMLEventReader eventReader) throws XMLStreamException
        Here load the values after parsing data using getter and setter methods.

    Parameters : 
        eventReader-Object of XMLEventReader

    Return : 
        location - object of location entity.

    Throws :
        XMLStreamException


See Also : 
            InvalidIPException.java - This is an user defined Exception will throw when passing invalid IP.
            Location.java           - This is an entity class.
            package-info.java       - This file used to create documentation.

