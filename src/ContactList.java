import java.util.ArrayList;
import java.util.Scanner;
public class ContactList {
    private final Scanner scanner = new Scanner(System.in);
    public ArrayList<Contact> contactList = new ArrayList<>();
    public boolean quit = false;
    private final String[] menuOptions = {
            "View contact list",
            "Add a new contact",
            "Remove a contact",
            "Quit"
    };

    public ContactList() {
        contactList.add(0, new Contact("Default", "999-222-4444"));
    }

    public void displayMainMenu() {
        for (int i = 0; i < menuOptions.length; i++) {
            System.out.printf("%s. %s\n", i+1, menuOptions[i]);
        }
        int userResponse = verifyInput(menuOptions.length);
        switch (userResponse) {
            //adjust cases by one to comply with index adjustment in verifyInput
            case 0 -> displayList();
            case 1 -> createContact();
            case 2 -> removeContact();
            case 3 -> quit = true;
        }
    }

    public void displayList() {
        if (contactList.size() == 0) {
            System.out.println("No contacts to display.");
        } else {
            for (int i = 0; i < contactList.size(); i++) {
                System.out.printf("%s. %s, %s\n", i+1, contactList.get(i).getContactName(), contactList.get(i).getPhoneNumber());
            }
        }
        System.out.println();
    }

    public void createContact() {
        System.out.println("Enter the new contact's name:");
        String name = capitalize(scanner.next());
        System.out.printf("Enter %s's phone number:\n", name);
        String phoneNumber = scanner.next();

        while (phoneNumber.length() != 10) {
            System.out.println("Please enter a 10-digit phone number");
            phoneNumber = scanner.next();
        }
        phoneNumber = formatPhoneNumber(phoneNumber);

        contactList.add(new Contact(name, phoneNumber));
        int i = contactList.size() - 1;
        System.out.println("New contact added:");
        System.out.printf("%s, %s\n\n", contactList.get(i).getContactName(), contactList.get(i).getPhoneNumber());
    }

    public String capitalize(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public String formatPhoneNumber(String unformatted) {
        if (unformatted == null || unformatted.length() == 0) {
            return unformatted;
        }
        return unformatted.substring(0,3).concat("-") + unformatted.substring(3,6).concat("-") + unformatted.substring(6, 10);
    }

    public void removeContact() {
        if (contactList.size() == 0) {
            System.out.println("No contacts to remove.");
        } else {
            displayList();
            System.out.println("Select the contact you'd like to remove:");
            int userResponse = verifyInput(contactList.size());
            System.out.printf("Removing %s from contact list...\n", contactList.get(userResponse).getContactName());
            contactList.remove(userResponse);
        }
        System.out.println();
    }

    public int verifyInput(int optionCount) {
        final String[] errorMessages = {
                "Must be between 1 and " + optionCount,
                "Must be a number between 1 and " + optionCount,
                "Only one menu option available"
        };
            //confirm input is an integer
            while (!scanner.hasNextInt()) {
                System.out.println(errorMessages[1]);
                scanner.next();
            }
            int userResponse = scanner.nextInt();
            //confirm  the integer isn't too small or too big
            while (userResponse <= 0 || userResponse > optionCount) {
                if (optionCount == 1) {
                    System.out.println(errorMessages[2]);
                } else {
                    System.out.println(errorMessages[0]);
                }
                //confirm the input is still an integer
                while (!scanner.hasNextInt()) {
                    System.out.println(errorMessages[1]);
                    scanner.next();
                }
                userResponse = scanner.nextInt();
            }
        //subtract 1 to adjust for index
        return userResponse - 1;
    }
}
