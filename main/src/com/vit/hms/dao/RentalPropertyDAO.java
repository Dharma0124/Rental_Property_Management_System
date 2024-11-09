package com.vit.hms.dao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.vit.hms.bean.RentalPropertyBean;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class RentalPropertyDAO {
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public RentalPropertyDAO() {
        // Use the new MongoClient
        this.mongoClient = MongoClients.create("mongodb://localhost:27017");
        this.database = mongoClient.getDatabase("HouseManagement");
        this.collection = database.getCollection("RENTAL_TBL");
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
            RentalPropertyBean property = new RentalPropertyBean();
            property.setPropertyId(doc.getString("PROPERTYID"));
            property.setRentalAmount(doc.getInteger("RENTALAMOUNT"));
            property.setNoOfBedrooms(doc.getInteger("NOOFBEDROOMS"));
            property.setLocation(doc.getString("LOCATION"));
            property.setCity(doc.getString("CITY"));
            properties.add(property);
        }
        return properties;
    }

    public void insertProperty(RentalPropertyBean property) {
        Document doc = new Document("PROPERTYID", property.getPropertyId())
                .append("RENTALAMOUNT", property.getRentalAmount())
                .append("NOOFBEDROOMS", property.getNoOfBedrooms())
                .append("LOCATION", property.getLocation())
                .append("CITY", property.getCity());
        collection.insertOne(doc);
    }
}
