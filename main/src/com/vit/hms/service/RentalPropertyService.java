package com.vit.hms.service;

import com.vit.hms.bean.RentalPropertyBean;
import com.vit.hms.dao.RentalPropertyDAO;
import java.util.List;

public class RentalPropertyService {
    private RentalPropertyDAO rentalPropertyDAO;

    public RentalPropertyService() {
        this.rentalPropertyDAO = new RentalPropertyDAO();
    }

    public void addRentalProperty(RentalPropertyBean property) {
        rentalPropertyDAO.addRentalProperty(property);
    }
    public List<RentalPropertyBean> fetchAllProperties() {
        return rentalPropertyDAO.getAllProperties();
    }

    public String fetchRentalProperty(int minRent, int maxRent, int bedrooms, String location, String city) {
        for (RentalPropertyBean property : rentalPropertyDAO.getAllProperties()) {
            if (property.getRentalAmount() >= minRent && property.getRentalAmount() <= maxRent &&
                property.getNoOfBedrooms() == bedrooms && property.getLocation().equals(location) &&
                property.getCity().equals(city)) {
                return "Property ID: " + property.getPropertyId();
            }
        }
        return "No matching property found.";
    }
}
