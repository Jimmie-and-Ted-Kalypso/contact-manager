import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Contact {

    Contact (String name) {
        this.name = name;
    }

    Contact (String name,int number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    private String name;
    private int number;



    public static void getContacts(Path dataDirectory) throws IOException {
        Scanner in = new Scanner(System.in);
        List<String> contacts = Files.readAllLines(dataDirectory);
        for (int i = 0; i < contacts.size(); i += 1) {
            String spaces;
            if (i==0){
                System.out.println(contacts.get(i));
                continue;
            }
            System.out.println((i) + ": " + contacts.get(i));
        }
        System.out.println("\nHit enter to return to home screen");
        in.nextLine();
    }
    public static void addContact(Path dataDirectory) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the name of the contact you want to add.");
        String contactName = in.nextLine();
        System.out.println("Enter the phone number of the contact you want to add.");
        String phoneNumberDigits = in.nextLine();
        String phoneNumber="";
        //Will check to make sure phone number is either 7 or 10 digits, and will add hyphens accordingly.
        if (phoneNumberDigits.length()!=7&&phoneNumberDigits.length()!=10){
            System.out.println("Phone number was not 7 or 10 digits, re-enter contact with a valid number");
            addContact(dataDirectory);
        }else {
            if (phoneNumberDigits.length() == 7) {
                for (int i = 0; i < phoneNumberDigits.length(); i++) {
                    phoneNumber += phoneNumberDigits.charAt(i);
                    if (i == 2) {
                        phoneNumber += "-";
                    }
                }
            } else {
                for (int i = 0; i < phoneNumberDigits.length(); i++) {
                    phoneNumber += phoneNumberDigits.charAt(i);
                    if (i == 2 || i == 5) {
                        phoneNumber += "-";
                    }
                }
            }

            //The code below will create a string containing the name, phone number, and equal spacing to center names and numbers.
            String newContact = "";
            double contactNameSpaces = 20 - contactName.length();
            double phoneNumberLength = 20 - phoneNumber.length();
            if (contactNameSpaces % 2 == 0) {
                for (int i = 0; i <= contactNameSpaces / 2; i++) {
                    newContact += " ";
                }
                newContact += contactName;
                for (int i = 0; i <= contactNameSpaces / 2; i++) {
                    newContact += " ";
                }
            } else {
                for (int i = 0; i <= (contactNameSpaces / 2 + .5); i++) {
                    newContact += " ";
                }
                newContact += contactName;
                for (int i = 0; i <= (contactNameSpaces / 2 - .5); i++) {
                    newContact += " ";
                }
            }
            newContact += "|";

            if (phoneNumberLength % 2 == 0) {
                for (int i = 0; i <= phoneNumberLength / 2; i++) {
                    newContact += " ";
                }
                newContact += phoneNumber;
                for (int i = 0; i <= phoneNumberLength / 2; i++) {
                    newContact += " ";
                }
            } else {
                for (int i = 0; i <= (phoneNumberLength / 2 + .5); i++) {
                    newContact += " ";
                }
                newContact += phoneNumber;
                for (int i = 0; i <= (phoneNumberLength / 2 - .5); i++) {
                    newContact += " ";
                }
            }

            List<String> contacts = Files.readAllLines(dataDirectory);
            int Count = 0;
            for (int i = 0; i < contacts.size(); i++) {
                if (contacts.get(i).toLowerCase().contains(contactName.toLowerCase())) {
                    System.out.println(contacts.get(i));
                    Count++;

                }
            }
            if (Count > 0) {
                System.out.printf("There's already a contact named %s. Do you want to overwrite it? (Yes/No)\n", contactName);
                String feedback = in.nextLine();
                if (feedback.equalsIgnoreCase("no")) {
                    addContact(dataDirectory);
                } else {
                    List<String> contactsMenu = Arrays.asList(newContact);
                    Files.write(dataDirectory, contactsMenu, StandardOpenOption.APPEND);
                }
            } else {
                List<String> contactsMenu = Arrays.asList(newContact);
                Files.write(dataDirectory, contactsMenu, StandardOpenOption.APPEND);
            }
        }
    }

    public static void searchContacts(Path dataDirectory) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a contact name to search for.");
        String contactName = in.nextLine();
        List<String> contacts = Files.readAllLines(dataDirectory);
        int Count =0;
        for (int i=0; i<contacts.size(); i++){
            if (contacts.get(i).toLowerCase().contains(contactName.toLowerCase())){
                System.out.println(contacts.get(i));
                Count++;

            }
        }
        if (Count==0) {
            System.out.println("No contact of that name found");
        }
        System.out.println("\nHit enter to return to main menu");
        in.nextLine();





    }

    public static void deleteContacts(Path dataDirectory) throws IOException {
        Scanner in = new Scanner(System.in);
        List<String> contacts = Files.readAllLines(dataDirectory);
        for (int i = 0; i < contacts.size(); i += 1) {
            if (i == 0) {
                System.out.println(contacts.get(i));
                continue;
            }
            System.out.println((i) + ": " + contacts.get(i));
        }
            System.out.println("Enter the number of the contact you would you like to delete?");
            String s = in.nextLine();
            int input = Integer.valueOf(s);
            contacts.remove(input);
            Files.write(dataDirectory, contacts);

        }


}
