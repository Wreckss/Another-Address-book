public class Main {

    public static void main(String[] args) {
        ContactList contactList = new ContactList();

        System.out.println("\n2022 Contacts\n");
        do {
            contactList.displayMainMenu();
        } while (!contactList.quit);
        System.out.println("Goodbye!");
    }
}
