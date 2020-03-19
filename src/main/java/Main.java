
import sun.jvmstat.perfdata.monitor.protocol.file.FileMonitoredVm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Main {

    private static ArrayList<Media> mediaLists = new ArrayList<>();

    public static void main(String[] args) {

        Game apex = new Game("1", "Apex", 2018, new String[] {"Respawn Entertainment"});
        Game cod = new Game("2", "Call of Duty", 2019, new String[] {"Infinity Ward"});
        Game battlefield = new Game("3", "Battlefield", 2018, new String[] {"Dice LA"});

        Book hungerGames = new Book("1", "Hunger Games",  2003, new String[]{"Suzanne Collins"});
        Book harryPotter = new Book("2", "Harry Potter", 20004, new String[]{"JK Rowling"});
        Book spiderMan = new Book("3", "Spider-Man", 1968, new String[]{"Stan Lee"});

        Film snatch = new Film ("1", "Snatch", 2000, new String[] {"Guy Ritchie"});
        Film avengers = new Film("2", "Avengers", 2008, new String[] {"Marvel Comics"});

        Media<Game> myGames = new Media<>("My Games");
        myGames.addMedia(apex);
        myGames.addMedia(cod);
        myGames.addMedia(battlefield);

        Media<Book> myBooks = new Media<>("My Books");
        myBooks.addMedia(hungerGames);
        myBooks.addMedia(harryPotter);
        myBooks.addMedia(spiderMan);

        Media<Film> myFilms = new Media<>("My Films");
        myFilms.addMedia(snatch);
        myFilms.addMedia(avengers);

        mediaLists.add(myGames);
        mediaLists.add(myBooks);
        mediaLists.add(myFilms);


        mainMenu();


       // mainMenu(myGames);  // cant get the menu system to work when trying to get the user to input what list they want to use.
        // my method works to find the list as far as i can tell but cant get anything to run after


    }


    public static void javaObjectToJsonFile(Media createdMedia) { // works

        Scanner input = new Scanner(System.in);
        System.out.println("What media list are you dealing with? myGames, myBooks, myFilms, etc: ");

        System.out.println("What is the title of the item in " + createdMedia.getMediaName() +" you want to convert to a JSON file?: ");
        String entertainmentTitle = input.nextLine();

        System.out.println("What do you want to call the JSON file?");
        String jsonFileName = input.next();

        Entertainment foundEntertainment = createdMedia.findEntertainment(entertainmentTitle);

        ObjectsToJson convert = new ObjectsToJson();
        convert.objectToJsonFile(foundEntertainment,jsonFileName);



    }

    public static void javaObjectToJsonString(Media createdMedia) { //works

        Scanner input = new Scanner(System.in);
        System.out.println("What is the title of the item in " + createdMedia.getMediaName() +" you want to convert to a JSON String?: ");
        String entertainmentTitle = input.nextLine();

        Entertainment foundEntertainment = createdMedia.findEntertainment(entertainmentTitle);
        ObjectsToJson convert = new ObjectsToJson();
        convert.objectToJsonString(foundEntertainment);

    }

    public static void javaObjectToPrettyJsonFile(Media createdMedia) { //works

        Scanner input = new Scanner(System.in);
        System.out.println("What is the title of the item in " + createdMedia.getMediaName() +" you want to convert to a JSON file?: ");
        String entertainmentTitle = input.nextLine();

        System.out.println("What do you want to call the pretty JSON file?");
        String jsonFileName = input.next();

        Entertainment foundEntertainment = createdMedia.findEntertainment(entertainmentTitle);

        ObjectsToJson convert = new ObjectsToJson();

        convert.objectToPrettyJsonFile(foundEntertainment, jsonFileName);
    }

    public static void javaListToJsonFile(Media createdMedia) { // works

        Scanner input = new Scanner(System.in);
        System.out.println("What do you want to call the JSON file containing the list of " + createdMedia.getMediaName() + "?: ");
        String jsonFileName = input.nextLine();

        ObjectsToJson convert = new ObjectsToJson();
        convert.listOfJavaObjectsToJson(createdMedia, jsonFileName);
    }


// JSON TO JAVA METHODS------------

    public static void jsonFileToJavaObject() { //works

        Scanner input = new Scanner(System.in);
        System.out.println("What is the name of the JSON file you want to turn into an object?: ");
        String jsonFileName = input.nextLine();

        JsonToObjects convert = new JsonToObjects(); //works
        convert.jsonFileToObject(jsonFileName);

    }

    public static void jsonStringToJavaObject() { //works

        Scanner input = new Scanner(System.in);
        System.out.println("What is the name of the JSON string you want to turn into an object?: ");
        String jsonString = input.nextLine();

        JsonToObjects convert = new JsonToObjects();
        convert.jsonStringToObject(jsonString);

    }

    public static void jsonListToObjectArrayList() { //works

        Scanner input = new Scanner(System.in);
        System.out.println("What is the name of the JSON list filename you want to turn into an object?: ");
        String jsonArrayListFileName = input.nextLine();

        JsonToObjects convert = new JsonToObjects();
        convert.jsonArrayToObjectList(jsonArrayListFileName);

    }

// FIND OBJECT FROM JSON FILE VIA ID

    public static void printObjectFromJsonArray() {

        Scanner input = new Scanner(System.in);

        System.out.println("What JSON file is the entertainment located in?: ");
        String arrayListFileName = input.nextLine();

        System.out.println("What is the ID tag of the entertainment you are looking for?: ");
        String id = input.next();

        JsonToObjects convert = new JsonToObjects();
        List<Entertainment> entertainmentList = convert.returnBookList(arrayListFileName);



        boolean match = false;

        while (!match) {
            for (int i = 0; i < entertainmentList.size(); i++) {
                if (entertainmentList.get(i).getId().equals(id)) {

                    System.out.println(entertainmentList.get(i));
                    match = true;
                    break;
                }
            }
            if (!match) {
                System.out.println("Could not locate entertainment");
                break;
            }
        }
    }


// MENU METHODS----------------

    private static void mainMenu() {
        boolean quit = false;
        Scanner scanner = new Scanner(System.in);

        System.out.println("What is the name of the media list you want to use?");
        String mediaName = scanner.nextLine();

        Media createdMedia = findMedia(mediaName);
        if (createdMedia == null) {
            System.out.println("NOT FOUND");
            quit = true;
        }

        while (!quit) {
            System.out.println("0 to quit\n" +
                    "1 to convert Java to a JSON \n" +
                    "2 to convert JSON to Java\n" +
                    "3 to locate an item via it's ID\n " +
                    "Enter Action:");
            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 0:
                    System.out.println("Quitting Application");
                    quit = true;
                    break;
                case 1:
                    menuJavaToJson(createdMedia);
                case 2:
                    menuJsonToJava(createdMedia);
                    break;
                case 3:
                    printObjectFromJsonArray();
                    break;
            }
        }

    }

    private static void menuJavaToJson(Media createdMedia) {

        Scanner scanner = new Scanner(System.in);
        boolean quit = false;


        while (!quit) {

            System.out.println("0 to quit\n" +
                    "1 to convert a Java Object to a JSON File\n" +
                    "2 to convert a Java Object to a pretty JSON File\n" +
                    "3 to convert a Java Object to JSON String \n" +
                    "4 to convert a Java Object List to a Json File containing the list \n" +
                    "Enter Action:");
            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 0:
                    System.out.println("Going back to Main Menu");
                    quit = true;
                    break;
                case 1:
                    javaObjectToJsonFile(createdMedia);
                    break;
                case 2:
                    javaObjectToPrettyJsonFile(createdMedia);
                    break;
                case 3:
                    javaObjectToJsonString(createdMedia);
                    break;
                case 4:
                    javaListToJsonFile(createdMedia);
                    break;
            }
        }
    }

    private static void menuJsonToJava(Media createdMedia) {

        Scanner scanner = new Scanner(System.in);
        boolean quit = false;


        while (!quit) {

            System.out.println("0 to quit\n" +
                    "1 to convert a JSON File to a Java Object\n" +
                    "2 to convert a JSON String  to a Java Object\n" +
                    "3 to convert a JSON List to Java ArrayList \n" +
                    "Enter Action:");
            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 0:
                    System.out.println("Going back to Main Menu");
                    quit = true;
                    break;
                case 1:
                    jsonFileToJavaObject();
                    break;
                case 2:
                    jsonStringToJavaObject();
                    break;
                case 3:
                    jsonListToObjectArrayList();
                    break;
            }
        }
    }

    private static Media findMedia(String mediaName) {


        return mediaLists.stream()
                .filter(x -> x.getMediaName().equals(mediaName))
                .findFirst()
                .orElse(null);

    }
}

// json string to object prints book
//do the json file that are made into objects know where they belong? eg game or film

