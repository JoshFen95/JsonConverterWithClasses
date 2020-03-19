import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class JsonToObjects<T> {


    public void jsonFileToObject(String fileName) {


        if (findJsonFile(fileName)) {

            try {
                ObjectMapper mapper = new ObjectMapper();
                Entertainment entertainmentFromFile = mapper.readValue(Paths.get(fileName + ".json").toFile(), Entertainment.class);
                System.out.println(entertainmentFromFile);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("File does not exist");
        }
    }


    public void jsonStringToObject(String jsonString) {


        try {
            // create object mapper instance
            ObjectMapper mapper = new ObjectMapper();

            // convert JSON string to Book object
            Entertainment entertainmentFromString = mapper.readValue(jsonString, Entertainment.class);

            // print book
            System.out.println("Printing Object");
            System.out.println(entertainmentFromString);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void jsonArrayToObjectList(String arrayListFileName) {

        if (findJsonFile(arrayListFileName)) {

            try {
                // create object mapper instance
                ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                // convert JSON array to list of entertainment
                List<Media> newList = Arrays.asList(mapper.readValue(Paths.get(arrayListFileName + ".json").toFile(), Media.class));

                // print entertainment
                System.out.println("Printing List");
                Media media = newList.get(0);
                List<Entertainment> entertainmentList = media.getMyMedia();
                entertainmentList.forEach(System.out::println);
                System.out.println(entertainmentList.get(1));


            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("File does not exist");
        }

    }

    public List<Entertainment> returnBookList(String arrayListFileName) {


        if (findJsonFile(arrayListFileName)) {

            try {
                // create object mapper instance
                ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                // convert JSON array to list of books
                List<Media> newList = Arrays.asList(mapper.readValue(Paths.get(arrayListFileName + ".json").toFile(), Media.class));
                Media media = newList.get(0);
                List<Entertainment> entertainmentList = media.getMyMedia();
                return entertainmentList;

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    private boolean findJsonFile(String fileName) {

        File tmpFile = new File(fileName + ".json");
        boolean exists = tmpFile.exists();

        if (exists) {
            return true;
        } else {
            return false;
        }
    }
}
