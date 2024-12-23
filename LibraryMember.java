
public class LibraryMember {
    private String name;
    private String address;
    private String phoneNumber;
    private boolean hasLibraryCard;



    public LibraryMember(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.hasLibraryCard = false;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean hasLibraryCard() {
        return hasLibraryCard;
    }

    public void createLibraryCard() {
        this.hasLibraryCard = true;
    }
}
