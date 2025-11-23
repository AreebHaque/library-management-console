package com.library.library_management;

import com.library.library_management.model.Book;
import com.library.library_management.model.User;
import com.library.library_management.model.reservation;
import com.library.library_management.repository.BookRepository;
import com.library.library_management.repository.ReservationRepository;
import com.library.library_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class LibraryManagementApplication implements CommandLineRunner {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ReservationRepository reservationRepository;

	private Scanner scanner = new Scanner(System.in);
	private User currentUser = null;

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("🚀 VIT Bhopal Library Management System");
		System.out.println("=======================================");

		showMainMenu();
	}

	private void showMainMenu() {
		while (true) {
			if (currentUser == null) {
				showLoginMenu();
			} else {
				showUserMenu();
			}
		}
	}

	private void showLoginMenu() {
		System.out.println("\n🔐 LOGIN MENU");
		System.out.println("1. Login");
		System.out.println("2. Exit");
		System.out.print("Choose option: ");

		int choice = scanner.nextInt();
		scanner.nextLine(); // consume newline

		switch (choice) {
			case 1:
				login();
				break;
			case 2:
				System.out.println("Thank you for using Library Management System!");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid option!");
		}
	}

	private void login() {
		System.out.print("Username: ");
		String username = scanner.nextLine();
		System.out.print("Password: ");
		String password = scanner.nextLine();

		User user = userRepository.findByUsernameAndPassword(username, password);
		if (user != null) {
			currentUser = user;
			System.out.println("✅ Login successful! Welcome " + user.getUsername() + " (" + user.getRole() + ")");
		} else {
			System.out.println("❌ Invalid username or password!");
		}
	}

	private void showUserMenu() {
		if ("STUDENT".equals(currentUser.getRole())) {
			showStudentMenu();
		} else if ("LIBRARIAN".equals(currentUser.getRole())) {
			showLibrarianMenu();
		}
	}

	private void showStudentMenu() {
		System.out.println("\n🎓 STUDENT MENU");
		System.out.println("1. Browse Books");
		System.out.println("2. Search Books");
		System.out.println("3. Reserve Book");
		System.out.println("4. View My Reservations");
		System.out.println("5. Cancel Reservation");
		System.out.println("6. Logout");
		System.out.print("Choose option: ");

		int choice = scanner.nextInt();
		scanner.nextLine();

		switch (choice) {
			case 1:
				browseBooks();
				break;
			case 2:
				searchBooks();
				break;
			case 3:
				reserveBook();
				break;
			case 4:
				viewMyReservations();
				break;
			case 5:
				cancelReservation();
				break;
			case 6:
				logout();
				break;
			default:
				System.out.println("Invalid option!");
		}
	}

	private void showLibrarianMenu() {
		System.out.println("\n👨‍💼 LIBRARIAN MENU");
		System.out.println("1. Browse Books");
		System.out.println("2. Add New Book");
		System.out.println("3. Delete Book");
		System.out.println("4. View All Users");
		System.out.println("5. Add Student");
		System.out.println("6. View All Reservations");
		System.out.println("7. Logout");
		System.out.print("Choose option: ");

		int choice = scanner.nextInt();
		scanner.nextLine();

		switch (choice) {
			case 1:
				browseBooks();
				break;
			case 2:
				addBook();
				break;
			case 3:
				deleteBook();
				break;
			case 4:
				viewAllUsers();
				break;
			case 5:
				addStudent();
				break;
			case 6:
				viewAllReservations();
				break;
			case 7:
				logout();
				break;
			default:
				System.out.println("Invalid option!");
		}
	}

	private void browseBooks() {
		System.out.println("\n📚 ALL BOOKS");
		List<Book> books = bookRepository.findAll();

		if (books.isEmpty()) {
			System.out.println("No books available in the library.");
			return;
		}

		for (int i = 0; i < books.size(); i++) {
			Book book = books.get(i);
			String status = book.isAvailable() ? "✅ AVAILABLE" : "❌ RESERVED";
			System.out.println((i + 1) + ". " + book.getTitle() + " by " + book.getAuthor());
			System.out.println("   ISBN: " + book.getIsbn() + " | Status: " + status);
			System.out.println();
		}
	}

	private void searchBooks() {
		System.out.print("Enter search term (title/author): ");
		String query = scanner.nextLine();

		List<Book> books = bookRepository.findByTitleContainingIgnoreCase(query);

		if (books.isEmpty()) {
			System.out.println("No books found matching: " + query);
			return;
		}

		System.out.println("\n🔍 SEARCH RESULTS");
		for (int i = 0; i < books.size(); i++) {
			Book book = books.get(i);
			String status = book.isAvailable() ? "✅ AVAILABLE" : "❌ RESERVED";
			System.out.println((i + 1) + ". " + book.getTitle() + " by " + book.getAuthor());
			System.out.println("   ISBN: " + book.getIsbn() + " | Status: " + status);
			System.out.println();
		}
	}

	private void reserveBook() {
		if (!"STUDENT".equals(currentUser.getRole())) {
			System.out.println("❌ Only students can reserve books!");
			return;
		}

		browseBooks();
		System.out.print("Enter book number to reserve: ");
		int bookNumber = scanner.nextInt();
		scanner.nextLine();

		List<Book> books = bookRepository.findAll();
		if (bookNumber < 1 || bookNumber > books.size()) {
			System.out.println("❌ Invalid book number!");
			return;
		}

		Book book = books.get(bookNumber - 1);
		if (!book.isAvailable()) {
			System.out.println("❌ Book is already reserved!");
			return;
		}

		// Create reservation
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime expiry = now.plusHours(1);
		reservation reservation = new reservation(currentUser, book, now, expiry);
		reservationRepository.save(reservation);

		// Update book availability
		book.setAvailable(false);
		bookRepository.save(book);

		System.out.println("✅ Book reserved successfully! Expires at: " + expiry);
	}

	private void viewMyReservations() {
		List<reservation> reservations = reservationRepository.findByUserId(currentUser.getId());

		if (reservations.isEmpty()) {
			System.out.println("📭 You have no active reservations.");
			return;
		}

		System.out.println("\n📋 YOUR RESERVATIONS");
		for (int i = 0; i < reservations.size(); i++) {
			reservation res = reservations.get(i);
			System.out.println((i + 1) + ". " + res.getBook().getTitle());
			System.out.println("   Reserved: " + res.getReservationDate());
			System.out.println("   Expires: " + res.getExpiryDate());
			System.out.println("   Status: " + res.getStatus());
			System.out.println();
		}
	}

	private void cancelReservation() {
		viewMyReservations();
		System.out.print("Enter reservation number to cancel: ");
		int resNumber = scanner.nextInt();
		scanner.nextLine();

		List<reservation> reservations = reservationRepository.findByUserId(currentUser.getId());
		if (resNumber < 1 || resNumber > reservations.size()) {
			System.out.println("❌ Invalid reservation number!");
			return;
		}

		reservation reservation = reservations.get(resNumber - 1);
		reservation.setStatus("CANCELLED");
		reservationRepository.save(reservation);

		// Make book available again
		Book book = reservation.getBook();
		book.setAvailable(true);
		bookRepository.save(book);

		System.out.println("✅ Reservation cancelled successfully!");
	}

	private void addBook() {
		if (!"LIBRARIAN".equals(currentUser.getRole())) {
			System.out.println("❌ Only librarians can add books!");
			return;
		}

		System.out.print("Enter book title: ");
		String title = scanner.nextLine();
		System.out.print("Enter author: ");
		String author = scanner.nextLine();
		System.out.print("Enter ISBN: ");
		String isbn = scanner.nextLine();

		Book book = new Book(title, author, isbn);
		bookRepository.save(book);

		System.out.println("✅ Book added successfully!");
	}

	private void deleteBook() {
		if (!"LIBRARIAN".equals(currentUser.getRole())) {
			System.out.println("❌ Only librarians can delete books!");
			return;
		}

		browseBooks();
		System.out.print("Enter book number to delete: ");
		int bookNumber = scanner.nextInt();
		scanner.nextLine();

		List<Book> books = bookRepository.findAll();
		if (bookNumber < 1 || bookNumber > books.size()) {
			System.out.println("❌ Invalid book number!");
			return;
		}

		Book book = books.get(bookNumber - 1);

		// Delete reservations first
		List<reservation> reservations = reservationRepository.findByBookId(book.getId());
		reservationRepository.deleteAll(reservations);

		// Then delete book
		bookRepository.delete(book);

		System.out.println("✅ Book deleted successfully!");
	}

	private void viewAllUsers() {
		List<User> users = userRepository.findAll();

		System.out.println("\n👥 ALL USERS");
		for (User user : users) {
			System.out.println("- " + user.getUsername() + " (" + user.getRole() + ")");
		}
	}

	private void addStudent() {
		System.out.print("Enter username: ");
		String username = scanner.nextLine();
		System.out.print("Enter email: ");
		String email = scanner.nextLine();
		System.out.print("Enter password: ");
		String password = scanner.nextLine();

		User student = new User(username, email, password, "STUDENT");
		userRepository.save(student);

		System.out.println("✅ Student added successfully!");
	}

	private void viewAllReservations() {
		List<reservation> reservations = reservationRepository.findAll();

		System.out.println("\n📊 ALL RESERVATIONS");
		for (reservation res : reservations) {
			System.out.println("- " + res.getUser().getUsername() + " reserved " + res.getBook().getTitle());
			System.out.println("  Status: " + res.getStatus() + " | Expires: " + res.getExpiryDate());
		}
	}

	private void logout() {
		System.out.println("👋 Goodbye " + currentUser.getUsername() + "!");
		currentUser = null;
	}
}