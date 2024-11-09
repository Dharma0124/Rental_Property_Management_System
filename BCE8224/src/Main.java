import com.vit.hms.bean.RentalPropertyBean;
import com.vit.hms.service.RentalPropertyService;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        RentalPropertyService service = new RentalPropertyService();

        // 1. Test to check if the newly generated property ID is valid
        System.out.println("Test 1: Check if newly generated property ID is valid.");
        RentalPropertyBean newProperty = new RentalPropertyBean();
        newProperty.setPropertyId("NEW1234");
        newProperty.setRentalAmount(15000);
        newProperty.setNoOfBedrooms(2);
        newProperty.setLocation("Adyar");
        newProperty.setCity("Chennai");

        if (newProperty.getPropertyId().matches("^[A-Z]{3}[0-9]{4}$")) {
            System.out.println("Property ID is valid: " + newProperty.getPropertyId());
        } else {
            System.out.println("Property ID is invalid.");
        }

        // 2. Test to check if a new property with valid values is getting added successfully
        System.out.println("\nTest 2: Check if new property with valid values is added successfully.");
        service.addRentalProperty(newProperty);
        System.out.println("New property added with ID: " + newProperty.getPropertyId());

        // 3. Test to check if the DAO method returns the correct list of data (list with records)
        System.out.println("\nTest 3: Check if DAO method returns the correct list of data (list with records).");
        List<RentalPropertyBean> properties = service.fetchAllProperties();
        if (!properties.isEmpty()) {
            System.out.println("List of properties (with records):");
            for (RentalPropertyBean property : properties) {
                System.out.println("Property ID: " + property.getPropertyId() + ", Rental Amount: " + property.getRentalAmount());
            }
        } else {
            System.out.println("No properties found.");
        }

        // 4. Test to check if the DAO method returns the correct list of data (list with no records)
        System.out.println("\nTest 4: Check if DAO method returns the correct list of data (list with no records).");
        
        List<RentalPropertyBean> emptyProperties = service.fetchAllProperties();
        if (emptyProperties.isEmpty()) {
            System.out.println("No records found as expected.");
        } else {
            System.out.println("Properties found when expecting an empty list.");
        }

        // 5. Test to check if the fetchRentalProperty method of the service class returns expected values for different conditions
        System.out.println("\nTest 5: Check if fetchRentalProperty method returns expected values for different conditions.");

        String result1 = service.fetchRentalProperty(3000, 15000, 2, "Adyar", "Chennai");
        System.out.println("Fetch result for range 3000-15000, 2 bedrooms, location Adyar, city Chennai: " + result1);

        String result2 = service.fetchRentalProperty(25000, 30000, 2, "HSR", "Bengaluru");
        System.out.println("Fetch result for range 25000-30000, 2 bedrooms, location HSR, city Bengaluru: " + result2);

        String result3 = service.fetchRentalProperty(5000, 10000, 1, "Tambaram", "Chennai");
        System.out.println("Fetch result for range 5000-10000, 1 bedroom, location Tambaram, city Chennai: " + result3);
        
        //test result
        String result = new RentalPropertyService().fetchRentalProperty(3000, 15000, 2, "Adyar", "Chennai");
        System.out.println(result);
    }
}
