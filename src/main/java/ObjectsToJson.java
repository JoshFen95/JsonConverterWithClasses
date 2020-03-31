import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.nio.file.Paths;



public class ObjectsToJson {


    public void objectToJsonFile(Entertainment foundEntertainment, String jsonFileName) {


        if (foundEntertainment != null) {

            try {

                // Create object mapper instance
                ObjectMapper mapper = new ObjectMapper();

                // Convert book object passed to JSON File
                mapper.writeValue(Paths.get(jsonFileName + ".json").toFile(), foundEntertainment);
                System.out.println("Operation Completed");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("Could not complete operation");
        }
    }

    public void objectToJsonString(Entertainment foundEntertainment) {

        if (foundEntertainment != null) {
            try {

                // Convert book object passed to JSON String

                String json = new ObjectMapper().writeValueAsString(foundEntertainment);

                // print JSON string
                System.out.println("Your book information as a JSON String: " + json);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else {
            System.out.println("Could not complete operation");
        }
    }

    public void objectToPrettyJsonFile(Entertainment foundEntertainment, String jsonFileName) {

        // Jackson API can also write pretty print JSON by using the DefaultPrettyPrinter class

        if (foundEntertainment != null) {
            try {
                // Create object mapper instance
                ObjectMapper mapper = new ObjectMapper();

                // create an instance of the PrettyPrinter
                ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

                // convert book object to JSON file
                writer.writeValue(Paths.get(jsonFileName + ".json").toFile(), foundEntertainment);
                System.out.println("Operation Completed");

            } catch (Exception ex) {
                ex.printStackTrace();
            }


        } else {
            System.out.println("Could not complete operation");
        }
    }

    public void listOfJavaObjectsToJson(Media mediaType, String jsonFileName) {


        try {


            // create object mapper instance
            ObjectMapper mapper = new ObjectMapper();

            // create an instance of the PrettyPrinter
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());


            // converts books list to JSON file
            writer.writeValue(Paths.get(jsonFileName + ".json").toFile(), mediaType);
            System.out.println("Operation Completed");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
