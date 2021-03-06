import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Main {

    private static ArrayList<Media> mediaLists = new ArrayList<>();

    public static void main(String[] args) {

        Game apex = new Game("1", "Apex", 2018, new String[]{"Respawn Entertainment"});
        Game cod = new Game("2", "Call of Duty", 2019, new String[]{"Infinity Ward"});
        Game battlefield = new Game("3", "Battlefield", 2018, new String[]{"Dice LA"});

        Book hungerGames = new Book("1", "Hunger Games", 2003, new String[]{"Suzanne Collins"});
        Book harryPotter = new Book("2", "Harry Potter", 20004, new String[]{"JK Rowling"});
        Book spiderMan = new Book("3", "Spider-Man", 1968, new String[]{"Stan Lee"});

        Film snatch = new Film("1", "Snatch", 2000, new String[]{"Guy Ritchie"});
        Film avengers = new Film("2", "Avengers", 2008, new String[]{"Marvel Comics"});

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


    }


    public static void javaObjectToJsonFile(Media createdMedia) { // works

        Scanner input = new Scanner(System.in);

        System.out.println("What is the title of the item in " + createdMedia.getMediaName() + " you want to convert to a JSON file?: ");
        String entertainmentTitle = input.nextLine();

        System.out.println("What do you want to call the JSON file?");
        String jsonFileName = input.next();

        Entertainment foundEntertainment = createdMedia.findEntertainment(entertainmentTitle);

        ObjectsToJson convert = new ObjectsToJson();
        convert.objectToJsonFile(foundEntertainment, jsonFileName);


    }

    public static void javaObjectToJsonString(Media createdMedia) { //works

        Scanner input = new Scanner(System.in);
        System.out.println("What is the title of the item in " + createdMedia.getMediaName() + " you want to convert to a JSON String?: ");
        String entertainmentTitle = input.nextLine();

        Entertainment foundEntertainment = createdMedia.findEntertainment(entertainmentTitle);
        ObjectsToJson convert = new ObjectsToJson();
        convert.objectToJsonString(foundEntertainment);

    }

    public static void javaObjectToPrettyJsonFile(Media createdMedia) { //works

        Scanner input = new Scanner(System.in);
        System.out.println("What is the title of the item in " + createdMedia.getMediaName() + " you want to convert to a JSON file?: ");
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


    public static void anyJsonFileToObject() {
        Boolean quit = false;
        JsonToObjects convert = new JsonToObjects();
        Scanner input = new Scanner(System.in);

        while (!quit) {
            System.out.println("Are you converting a single object or an array of objects?\n0) to quit\n1)Single Object\n2)Array of Objects");
            int action = input.nextInt();

            switch (action) {
                case 0:
                    System.out.println("Quitting Application");
                    quit = true;
                    break;
                case 1:

                    System.out.println("What is the filename where the JSON data is located?: ");
                    String resourceFilePath = input.next() + ".json";
                    System.out.println("File Name: " + resourceFilePath);

                    System.out.println("What object would you like to create?\n1)Game\n2)Film\n3)Book");
                    action = input.nextInt();

                    switch (action) {
                        case 1:
                            Game newGame = (Game) convert.fetchObjectFromJson(resourceFilePath, Game.class);
                            if (newGame == null) {
                                System.out.println("JSON FILE DOES NOT CONTAIN GAME");
                            } else {
                                System.out.println(newGame + "\n");
                            }
                            break;

                        case 2:
                            Film newFilm = (Film) convert.fetchObjectFromJson(resourceFilePath, Film.class);
                            if (newFilm == null) {
                                System.out.println("JSON FILE DOES NOT CONTAIN FILM");
                            } else {
                                System.out.println(newFilm + "\n");
                            }

                            break;

                        case 3:
                            Book newBook = (Book) convert.fetchObjectFromJson(resourceFilePath, Book.class);
                            if (newBook == null) {
                                System.out.println("JSON FILE DOES NOT CONTAIN BOOK");
                            } else {
                                System.out.println(newBook + "\n");

                            }
                    }
                    break;
                case 2:

                    System.out.println("What is the filename where the JSON array is located?: ");
                    String resourceFilePathArray = input.next() + ".json";
                    System.out.println("File Name: " + resourceFilePathArray);

                    System.out.println("What object would you like to create?\n1)Game Array\n2)Film Array\n3)Book Array");
                    action = input.nextInt();
                    switch (action) {
                        case 1:
                            Game[] newGames = (Game[]) convert.fetchObjectFromJson(resourceFilePathArray, Game[].class);
                            if (newGames == null) {
                                System.out.println("JSON FILE DOES NOT CONTAIN FILM ARRAY");
                            } else {
                                Arrays.stream(newGames).forEach(game -> System.out.println(game));
                            }
                            break;

                        case 2:
                            Film[] newFilms = (Film[]) convert.fetchObjectFromJson(resourceFilePathArray, Film[].class);
                            if (newFilms == null) {
                                System.out.println("JSON FILE DOES NOT CONTAIN FILM ARRAY");
                            } else {
                                Arrays.stream(newFilms).forEach(film -> System.out.println(film));
                            }

                            break;

                        case 3:
                            Book[] newBooks = (Book[]) convert.fetchObjectFromJson(resourceFilePathArray, Book[].class);
                            if (newBooks == null) {
                                System.out.println("JSON FILE DOES NOT CONTAIN FILM ARRAY");
                            } else {
                                Arrays.stream(newBooks).forEach(book -> System.out.println(book));
                            }
                            break;

                    }
                    System.out.println();
            }

        }
    }

    //FIND OBJECT FROM JSON FILE VIA ID

    public static void printObjectFromJsonArray() {

        Scanner input = new Scanner(System.in);

        System.out.println("What JSON file is the entertainment located in?(Must be file containing array): ");
        String arrayListFileName = input.nextLine();

        System.out.println("What is the ID tag of the entertainment you are looking for?: ");
        int id = input.nextInt();

        JsonToObjects convert = new JsonToObjects();
        Entertainment[] mediaArray = convert.returnEntertainmentList(arrayListFileName);


        boolean match = false;

        while (!match) {
            for (int i = 0; i < mediaArray.length; i++) {
                if (mediaArray[i].getId().equals(id + "")) {

                    System.out.println(mediaArray[id - 1].toString());
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


        while (!quit) {
            System.out.println("0 to quit\n" +
                    "1 to convert Java to a JSON \n" +
                    "2 to convert JSON to Java\n" +
                    "3 to locate an item via it's ID\n" +
                    "Enter Action:");
            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 0:
                    System.out.println("Quitting Application");
                    quit = true;
                    break;
                case 1:
                    menuJavaToJson();
                    break;
                case 2:
                    anyJsonFileToObject();
                    break;
                case 3:
                    printObjectFromJsonArray();
                    break;
            }
        }

    }

    private static void menuJavaToJson() {

        Scanner scanner = new Scanner(System.in);
        boolean quit = false;

        System.out.println("What is the name of the media list you want to use?");
        String mediaName = scanner.nextLine();

        Media createdMedia = findMedia(mediaName);
        if (createdMedia == null) {
            System.out.println("NOT FOUND");
            quit = true;
        }


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



    private static Media findMedia(String mediaName) {


        return mediaLists.stream()
                .filter(x -> x.getMediaName().equals(mediaName))
                .findFirst()
                .orElse(null);

    }
}



