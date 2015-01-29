package com.mycompany.geolocation;

import java.io.IOException;

/**
 * Its an interface for Geolocator.
 * Interface declare the relevant methods.
 * @author techmachine
 */
public interface GeoLocator {
    /**
     * locate address using ip address.
     * @param ip is an parameter.
     * @return location object.
     * @throws IOException as message
     */
    Location locate(final String ip)  throws IOException;
}
