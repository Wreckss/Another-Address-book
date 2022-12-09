public class Contact {
    private final String contactName;
    private final String phoneNumber;

    public Contact(String name, String number) {
        contactName = name;
        phoneNumber = number;
    }

    public String getContactName() {
        return contactName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
