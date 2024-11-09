package com.vit.hms.bean;

public class RentalPropertyBean {
    private String propertyId;
    private int rentalAmount;
    private int noOfBedrooms;
    private String location;
    private String city;

    // Getters and setters for all properties
    public String getPropertyId() { return propertyId; }
    public void setPropertyId(String propertyId) { this.propertyId = propertyId; }
    public int getRentalAmount() { return rentalAmount; }
    public void setRentalAmount(int rentalAmount) { this.rentalAmount = rentalAmount; }
    public int getNoOfBedrooms() { return noOfBedrooms; }
    public void setNoOfBedrooms(int noOfBedrooms) { this.noOfBedrooms = noOfBedrooms; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
}
