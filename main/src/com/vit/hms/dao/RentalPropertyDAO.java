package com.vit.hms.dao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;

import com.vit.hms.bean.RentalPropertyBean;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

public class RentalPropertyDAO {
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public RentalPropertyDAO() {
        this.mongoClient = MongoClients.create("mongodb://localhost:27017");
        this.database = mongoClient.getDatabase("HouseManagement");
        this.collection = database.getCollection("RENTAL_TBL");

        // NEW: create indexes for faster searching
        ensureIndexes();
    }

    // NEW: indexes (MongoDB will not duplicate an existing identical index)
    private void ensureIndexes() {
        // Recommended: PROPERTYID should be unique
        collection.createIndex(Indexes.ascending("PROPERTYID"), new IndexOptions().unique(true));

        // Common search: city/location/bedrooms + rent range
        collection.createIndex(
                Indexes.compoundIndex(
                        Indexes.ascending("CITY", "LOCATION", "NOOFBEDROOMS", "RENTALAMOUNT")
                )
        );

        // When some fields are missing, a smaller index can still help (city + rent)
        collection.createIndex(
                Indexes.compoundIndex(
                        Indexes.ascending("CITY", "RENTALAMOUNT")
                )
        );
    }

    public void addRentalProperty(RentalPropertyBean property) {
        Document doc = new Document("PROPERTYID", property.getPropertyId())
                .append("RENTALAMOUNT", property.getRentalAmount())
                .append("NOOFBEDROOMS", property.getNoOfBedrooms())
                .append("LOCATION", property.getLocation())
                .append("CITY", property.getCity());
        collection.insertOne(doc);
    }

    public List<RentalPropertyBean> getAllProperties() {
        List<RentalPropertyBean> properties = new ArrayList<>();
        for (Document doc : collection.find()) {
            properties.add(toBean(doc));
        }
        return properties;
    }

    // NEW: dynamic search (supports missing fields)
    public List<RentalPropertyBean> searchProperties(
            Integer minRent,
            Integer maxRent,
            Integer bedrooms,
            String location,
            String city
    ) {
        List<Bson> filters = new ArrayList<>();

        // Add filters only if the user provided them
        if (minRent != null) {
            filters.add(Filters.gte("RENTALAMOUNT", minRent));
        }
        if (maxRent != null) {
            filters.add(Filters.lte("RENTALAMOUNT", maxRent));
        }
        if (bedrooms != null) {
            filters.add(Filters.eq("NOOFBEDROOMS", bedrooms));
        }
        if (location != null && !location.trim().isEmpty()) {
            filters.add(Filters.eq("LOCATION", location.trim()));
        }
        if (city != null && !city.trim().isEmpty()) {
            filters.add(Filters.eq("CITY", city.trim()));
        }

        Bson query = filters.isEmpty() ? new Document() : Filters.and(filters);

        List<RentalPropertyBean> results = new ArrayList<>();
        for (Document doc : collection.find(query)) {
            results.add(toBean(doc));
        }
        return results;
    }

    // Helper to convert Document -> Bean
    private RentalPropertyBean toBean(Document doc) {
        RentalPropertyBean property = new RentalPropertyBean();
        property.setPropertyId(doc.getString("PROPERTYID"));
        property.setRentalAmount(doc.getInteger("RENTALAMOUNT"));
        property.setNoOfBedrooms(doc.getInteger("NOOFBEDROOMS"));
        property.setLocation(doc.getString("LOCATION"));
        property.setCity(doc.getString("CITY"));
        return property;
    }

    // Keep your old method name, but avoid duplicate code
    public void insertProperty(RentalPropertyBean property) {
        addRentalProperty(property);
    }
}
