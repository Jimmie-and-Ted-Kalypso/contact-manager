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
        String phoneNumber = in.nextLine();
        String newContact = contactName + "  |  "+phoneNumber;
        List<String> contactsMenu = Arrays.asList(newContact);
        Files.write(dataDirectory, contactsMenu, StandardOpenOption.APPEND);
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
