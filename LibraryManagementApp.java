import java.time.LocalDate;
import java.util.Scanner;

public class LibraryManagementApp {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        // Adding sample books to the catalog
        library.addBook(new Book("Java Programming", "John Doe", "978-1234567890", 5));
        library.addBook(new Book("Python Essentials", "Jane Smith", "978-0987654321", 3));

        // Create a dummy LibraryMember object initially (this will be updated later)
        LibraryMember member = null;

        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Create Library Card");
            System.out.println("2. Add a Book");
            System.out.println("3. Issue a Book");
            System.out.println("4. Return a Book");
            System.out.println("5. View All Books");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Take user input for library card creation
                    System.out.println("Please enter your details to create a library card:");

                    // Prompt for user input
                    scanner.nextLine(); // To consume newline character after nextInt()
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter your address: ");
                    String address = scanner.nextLine();
                    System.out.print("Enter your phone number: ");
                    String phoneNumber = scanner.nextLine();

                    // Create a new LibraryMember object with the provided details
                    member = new LibraryMember(name, address, phoneNumber);

                    // Create the library card
                    library.createLibraryCard(member);
                    break;

                case 2:
                    System.out.print("Enter title: ");
                    scanner.nextLine();  // Consume newline
                    String title = scanner.nextLine();
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    library.addBook(new Book(title, author, isbn, quantity));
                    System.out.println("Book added successfully!");
                    break;

                case 3:
                    System.out.print("Enter ISBN: ");
                    isbn = scanner.next();
                    System.out.print("Enter issue date (yyyy-mm-dd): ");
                    LocalDate issueDate = LocalDate.parse(scanner.next());
                    System.out.print("Enter due date (yyyy-mm-dd): ");
                    LocalDate dueDate = LocalDate.parse(scanner.next());
                    library.issueBook(isbn, issueDate, dueDate, member);
                    break;

                case 4:
                    System.out.print("Enter ISBN: ");
                    isbn = scanner.next();
                    System.out.print("Enter return date (yyyy-mm-dd): ");
                    LocalDate returnDate = LocalDate.parse(scanner.next());
                    library.returnBook(isbn, returnDate);
                    break;

                case 5:
                    library.displayBooks();
                    break;

                case 6:
                    System.out.println("Exiting the system.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
