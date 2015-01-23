/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.geolocation;

/**
 * Entity fields Declaration and Initialization.
 * @author techmachine.
 */
public class Location {
    /**
     * Field for IP Address.
     */
    private String ipValue;
    /**
     * Field for Country Code.
     */
    private String countryCode;
    /**
     * Field for Country Name.
     */
    private String countryName;
    /**
     * Field for Region Code.
     */
    private String regionCode;
    /**
     * Field for Region Name.
     */
    private String regionName;
    /**
     * Field for City Name.
     */
    private String cityName;
    /**
     * Field for Zip Code.
     */
    private String zipCode;
    /**
     * Field for Time Zone.
     */
    private String timeZone;
    /**
     * Field for Latitude.
     */
    private String latitudeValue;
    /**
     * Field for Longitude.
     */
    private String longitudeValue;
    /**
     * Field for Metro Code.
     */
    private String metroCode;
    /**
     * Get IP Value.
     * @return IP Value.
     */
    public String getIpValue() {
        return ipValue;
    }
    /**
     * Set IP value.
     * @param ipValueParams as string.
     */
    public void setIpValue(final String ipValueParams) {
        this.ipValue = ipValueParams;
    }
    /**
     * Get Country Code.
     * @return a countryCode
     */
    public String getCountryCode() {
        return countryCode;
    }
    /**
     * Set country code.
     * @param countryCodeParams 
     */
    public void setCountryCode(final String countryCodeParams) {
        this.countryCode = countryCodeParams;
    }
    /**
     * Get country name.
     * @return a countryName
     */
    public String getCountryName() {
        return countryName;
    }
    /**
     * set country name.
     * @param countryNameParams 
     */
    public void setCountryName(final String countryNameParams) {
        this.countryName = countryNameParams;
    }
    /**
     * get region code.
     * @return a regionCode
     */
    public String getRegionCode() {
        return regionCode;
    }
    /**
     * set region code.
     * @param regionCodeParams 
     */
    public void setRegionCode(final String regionCodeParams) {
        this.regionCode = regionCodeParams;
    }
    /**
     * Get Region Code.
     * @return a regionName
     */
    public String getRegionName() {
        return regionName;
    }
    /**
     * Set Region Name.
     * @param regionNameParams 
     */
    public void setRegionName(final String regionNameParams) {
        this.regionName = regionNameParams;
    }
    /**
     * Get City Name.
     * @return a cityName
     */
    public String getCityName() {
        return cityName;
    }
    /**
     * Set City Name.
     * @param cityNameParams 
     */
    public void setCityName(final String cityNameParams) {
        this.cityName = cityNameParams;
    }
    /**
     * Get Zip Code.
     * @return a zipCode
     */
    public String getZipCode() {
        return zipCode;
    }
    /**
     * Set Zip Code.
     * @param zipCodeParams 
     */
    public void setZipCode(final String zipCodeParams) {
        this.zipCode = zipCodeParams;
    }
    /**
     * Get Time Zone.
     * @return a timeZone
     */
    public String getTimeZone() {
        return timeZone;
    }
    /**
     * Set Time Zone.
     * @param timeZoneParams 
     */
    public void setTimeZone(final String timeZoneParams) {
        this.timeZone = timeZoneParams;
    }
    /**
     * Get latitude.
     * @return a latitudeValue
     */
    public String getLatitudeValue() {
        return latitudeValue;
    }
    /**
     * Set latitude.
     * @param latitudeValueParams 
     */
    public void setLatitudeValue(final String latitudeValueParams) {
        this.latitudeValue = latitudeValueParams;
    }
    /**
     * Get Longitude.
     * @return a longitudeValue
     */
    public String getLongitudeValue() {
        return longitudeValue;
    }
    /**
     * Set Longitude.
     * @param longitudeValueParams 
     */
    public void setLongitudeValue(final String longitudeValueParams) {
        this.longitudeValue = longitudeValueParams;
    }
    /**
     * Get Metro Code.
     * @return a metroCode.
     */
    public String getMetroCode() {
        return metroCode;
    }
    /**
     * Set Metro Code.
     * @param metroCodeParams 
     */
    public void setMetroCode(final String metroCodeParams) {
        this.metroCode = metroCodeParams;
    }
}
