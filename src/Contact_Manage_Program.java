import java.util.Scanner;

public class Contact_Manage_Program {
    public static void main(String[] args) {
        do {
        System.out.println("1. View contacts.\n" +
        "2. Add a new contact.\n" +
        "3. Search a contact by name.\n" +
        "4. Delete an existing contact.\n" +
        "5. Exit.");
        System.out.print("Enter an option (1, 2, 3, 4 or 5): ");
        Scanner in = new Scanner(System.in);
        String s = in.nextLine().trim();
        try {int menu = Integer.valueOf(s);
        if (menu == 1) {
            System.out.println("You have selected view contacts, but it isn't implemented yet.");
        }
        else if(menu == 2) {
            System.out.println("You have selected Add a new contact, but it isn't implemented yet.");
        }
        else if (menu == 3) {
            System.out.println("You have selected Search a contact by name, but it isn't implemented yet.");
        }
        else if (menu == 4) {
            System.out.println("You have selected Delete an existing contact, but it isn't implemented yet.");
        }
        else if (menu == 5) {
            System.out.println("Thank you, goodbye.");
            break;
        }
        else {
            System.out.println("I'm sorry, that selection doesn't match any menu options. Type \"5\" to exit.");
        }
        } catch (Exception e) {
            System.out.println("I'm sorry, you must enter a number, please try again.");
        }
        } while (true);

    }
}
