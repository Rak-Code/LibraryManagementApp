import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Scanner;
import java.time.LocalDate;


public class Library {
    private HashMap<String, Book> catalog = new HashMap<>();
    private HashMap<String, LibraryMember> members = new HashMap<>();
    private static final double DAILY_ISSUE_CHARGE = 10.0;
    private static final double DAILY_FINE = 1.50;
    private static final double LIBRARY_CARD_CHARGE = 100.0;
    private static final String UPI_ID = "library@upi";

    // Add a book to the catalog
    public void addBook(Book book) {
        catalog.put(book.getIsbn(), book);
    }

    // Issue a book
    public void issueBook(String isbn, LocalDate issueDate, LocalDate dueDate, LibraryMember member) {
        if (!member.hasLibraryCard()) {
            System.out.println("You need a library card to issue books.");
            return;
        }

        Book book = catalog.get(isbn);
        if (book != null && book.getQuantity() > book.getIssued()) {
            long days = ChronoUnit.DAYS.between(issueDate, dueDate);
            double charges = days * DAILY_ISSUE_CHARGE;

            System.out.printf("Book issued successfully! Charges for %d days: ₹%.2f\n", days, charges);
            book.setIssued(book.getIssued() + 1);
            book.setIssueDate(issueDate);
            book.setDueDate(dueDate);
        } else {
            System.out.println("Book not available!");
        }
    }

    // Return a book and calculate fines
    public void returnBook(String isbn, LocalDate returnDate) {
        Book book = catalog.get(isbn);
        if (book != null && book.getIssued() > 0) {
            book.setIssued(book.getIssued() - 1);
            long overdueDays = ChronoUnit.DAYS.between(book.getDueDate(), returnDate);
            double fine = overdueDays > 0 ? overdueDays * DAILY_FINE : 0;

            if (fine > 0) {
                System.out.printf("Book returned late. Fine: ₹%.2f\n", fine);
                processPayment(fine);
            } else {
                System.out.println("Book returned successfully!");
            }
        } else {
            System.out.println("Invalid return attempt!");
        }
    }

    // Process Payment
    public void processPayment(double amount) {
        System.out.println("Select Payment Method:\n1. UPI\n2. Cash");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Pay to this UPI ID: " + UPI_ID);
                break;
            case 2:
                System.out.println("Pay ₹" + amount + " in cash at the counter.");
                break;
            default:
                System.out.println("Invalid payment option!");
        }
    }

    // Create a library card
    public void createLibraryCard(LibraryMember member) {
        if (member.hasLibraryCard()) {
            System.out.println("You already have a library card!");
            return;
        }

        System.out.printf("Library Card Charge: ₹%.2f\n", LIBRARY_CARD_CHARGE);
        processPayment(LIBRARY_CARD_CHARGE);
        member.createLibraryCard();
        System.out.println("Library card created successfully!");
    }

    // Display all books
    public void displayBooks() {
        System.out.println("Available Books:");
        for (Book book : catalog.values()) {
            System.out.printf("ISBN: %s, Title: %s, Author: %s, Quantity: %d, Issued: %d\n",
                book.getIsbn(), book.getTitle(), book.getAuthor(), book.getQuantity(), book.getIssued());
        }
    }
}
