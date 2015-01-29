package com.mycompany.geolocation;

/**
 * InvalidIPException.
 * @author techmachine
 */
public class InvalidIPException extends Exception {
    /**
     * Exception throwing as string.
     * 
     * @param s is string object.
     */
    InvalidIPException(final String s) {
      super(s);
   }
}
