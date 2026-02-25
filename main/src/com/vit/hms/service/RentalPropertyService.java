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

    /**
     * UPDATED: now queries MongoDB directly (fast) instead of loading all docs and filtering in Java.
     * Supports missing fields: pass null for missing numeric fields, and null/"" for missing strings.
     */
    public String fetchRentalProperty(Integer minRent, Integer maxRent, Integer bedrooms, String location, String city) {
        List<RentalPropertyBean> matches =
                rentalPropertyDAO.searchProperties(minRent, maxRent, bedrooms, location, city);

        if (matches == null || matches.isEmpty()) {
            return "No matching property found.";
        }

        // keep the old behavior: return first match's ID
        return "Property ID: " + matches.get(0).getPropertyId();
    }
}
