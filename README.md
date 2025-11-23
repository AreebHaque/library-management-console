# Library Management System

A console based Library Management System built with Spring Boot to automate library operations.This system provides an efficient digital solution for book reservations, user management, and library inventory tracking. 

### Features

####  Authentication & Authorization
- Role-based access control (Student & Librarian)
- Secure session management
- Automatic login redirection

#### Book Management
- Browse all available books
- Advanced search functionality (title, author, ISBN)
- Real-time availability status
- Book reservation system

#### Smart Reservation System
- 1-hour automatic reservation expiry
- Scheduled cleanup of expired reservations
- Reservation cancellation capability
- Real-time status updates

#### Admin Dashboard
- Complete book CRUD operations
- Student account management
- System monitoring
- Manual reservation management

### Technology Used

#### Backend
- **Spring Boot 3.5.8** - Application framework
- **Spring MVC** - Web framework
- **Spring Data JPA** - Database abstraction
- **Spring Security** - Authentication
- **Java 21** - Programming language

#### Database & Tools
- **H2 Database** - Embedded database
- **Maven** - Build tool
- **Git** - Version control

### System Architecture
```
library-management-console/
├── .mvn/
│   └── wrapper/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── library/
│       │           └── library_management/
│       │               ├── config/
│       │               │   └── DataLoader.java
│       │               ├── model/
│       │               │   ├── Book.java
│       │               │   ├── User.java
│       │               │   └── Reservation.java
│       │               ├── repository/
│       │               │   ├── BookRepository.java
│       │               │   ├── UserRepository.java
│       │               │   └── ReservationRepository.java
│       │               ├── service/
│       │               │   └── ReservationCleanupService.java
│       │               └── LibraryManagementApplication.java
│       └── resources/
│           └── application.properties
├── target/
├── .gitignore
├── HELP.md
├── mvnw
├── mvnw.cmd
└── pom.xml
```





### Steps to Install and run the project

#### Prerequisites
- **Java JDK 17 or higher** - [Download here](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- **Apache Maven 3.6+** - [Download here](https://maven.apache.org/download.cgi)
- **Git** - [Download here](https://git-scm.com/downloads)


#### Step 1: Clone the Repository

```bash
# Clone the project from GitHub
git clone https://github.com/AreebHaque/library-management-console.git

# Navigate to project directory
cd library-management-console
```
#### Step 2: Verify Java and Maven Installation

```bash
# Check Java version (should be 17 or higher)
java -version

# Check Maven version (should be 3.6 or higher)
mvn -version
```
#### Step 3: Build the Project
```bash
# Clean and compile the project
mvn clean compile

# Or build the complete package (includes tests)
mvn clean package -DskipTests
```
#### Step 4: Run the Application
```bash
# Method 1: Using Maven (recommended)
mvn spring-boot:run

# Method 2: Using the JAR file
java -jar target/library-management-0.0.1-SNAPSHOT.jar
```
#### Step 5: Use the Application

Once running, you'll see:

```bash
  VIT Bhopal Library Management System
=======================================

LOGIN MENU
1. Login
2. Exit
Choose option:
```
#### How to Navigate

Student Menu Options:

    Browse Books - View all available books

    Search Books - Search by title/author

    Reserve Book - Reserve available books

    View My Reservations - Check your reservations

    Cancel Reservation - Cancel active reservations

    Logout - Exit to login menu

Librarian Menu Options:

    Browse Books - View all books

    Add New Book - Add books to library

    Delete Book - Remove books from system

    View All Users - See registered users

    Add Student - Create new student accounts

    View All Reservations - Monitor all reservations

    Logout - Exit to login menu

#### Troubleshooting

Common Issues:

    "mvn not found": Maven not installed or not in PATH

    "Java version error": Install JDK 17 or higher

    "Port already in use": Change port in application.properties

    Build failures: Run mvn clean install -U

#### Success Indicators

    Application starts without errors

    Login menu appears in console

    Can login with test accounts

    All menu options work correctly

    Database file created at ./data/librarydb.mv.db
## Instructions for testing

### Test Accounts

The system comes with pre-configured test accounts:

| Role      | Username    | Password | Purpose                         |
|-----------|-------------|----------|---------------------------------|
| Student   | student1    | pass123  | Testing student features        |
| Student   | student2    | pass123  | Additional test account         |
| Librarian | librarian1  | admin123 | Testing admin features          |

### Test Scenarios

#### Test Case 1: Successful Login
1. **Start application** - Run `mvn spring-boot:run`
2. **Choose option 1** (Login)
3. **Enter credentials**: `student1` / `pass123`
4. **Expected Result**: "Login successful! Welcome student1 (STUDENT)"
5. **Repeat** with librarian account

#### Test Case 2: Invalid Login
1. **Choose option 1** (Login)
2. **Enter wrong credentials**: `wronguser` / `wrongpass`
3. **Expected Result**: "Invalid username or password!"
4. **Verify** login menu reappears

#### Test Case 3: Logout Functionality
1. **Login successfully**
2. **Choose logout option** (6 for students, 7 for librarians)
3. **Expected Result**: "Goodbye [username]!" and return to login menu

### Book Operations Testing

#### Test Case 4: Browse All Books
1. **Login as student**
2. **Choose option 1** (Browse Books)
3. **Expected Result**: List of all books with titles, authors, ISBN, and availability status
4. **Verify** both available and reserved books show correct status

#### Test Case 5: Search Books
1. **Login as student**
2. **Choose option 2** (Search Books)
3. **Enter search term**: "Java" or partial title/author
4. **Expected Result**: Filtered list of matching books
5. **Test empty search** - should return all books

#### Test Case 6: Book Reservation (Student)
1. **Browse books** and note an available book
2. **Choose option 3** (Reserve Book)
3. **Enter book number** of available book
4. **Expected Result**: "Book reserved successfully! Expires at: [timestamp]"
5. **Verify** book status changes to reserved when browsing

#### Test Case 7: View Reservations
1. **After reserving a book**
2. **Choose option 4** (View My Reservations)
3. **Expected Result**: List of your active reservations with details
4. **Verify** reservation shows correct book and expiry time

### Reservation System Testing

#### Test Case 8: Cancel Reservation
1. **View your reservations**
2. **Choose option 5** (Cancel Reservation)
3. **Enter reservation number** to cancel
4. **Expected Result**: "Reservation cancelled successfully!"
5. **Verify** book becomes available again

#### Test Case 9: Reservation Limits
1. **Try to reserve** an already reserved book
2. **Expected Result**: "Book is already reserved!"
3. **Verify** appropriate error message

### Admin Functionality Testing

#### Test Case 10: Librarian Access Control
1. **Login as student** - try to access admin features
2. **Expected Result**: "Only librarians can [action]!"
3. **Login as librarian** - verify full access

#### Test Case 11: Add New Book
1. **Login as librarian**
2. **Choose option 2** (Add New Book)
3. **Enter details**: Title, Author, ISBN
4. **Expected Result**: "Book added successfully!"
5. **Browse books** to verify new book appears

#### Test Case 12: Delete Book
1. **Browse books** as librarian
2. **Choose option 3** (Delete Book)
3. **Enter book number** to delete
4. **Expected Result**: "Book deleted successfully!"
5. **Verify** book removed from list

#### Test Case 13: User Management
1. **Login as librarian**
2. **Choose option 4** (View All Users)
3. **Expected Result**: List of all registered users with roles
4. **Choose option 5** (Add Student) - create new test account

### Database Testing

#### Test Case 14: Data Persistence
1. **Add a book** as librarian
2. **Restart the application**
3. **Browse books** - verify book still exists
4. **Verify** all user accounts persist

#### Test Case 15: Reservation Cleanup
1. **Create a reservation**
2. **Wait for scheduled cleanup** (runs automatically)
3. **Or test manually** by modifying expiry time in code
4. **Verify** expired reservations get proper status

### System Testing

#### Test Case 16: Error Handling
1. **Enter invalid menu options** (e.g., 99)
2. **Expected Result**: "Invalid option!" with menu redisplay
3. **Enter invalid book numbers** - verify error messages
4. **Test boundary conditions**

#### Test Case 17: Input Validation
1. **Try to add book** with empty fields
2. **Expected Result**: Appropriate validation/error handling
3. **Test special characters** in inputs

### Test Completion Checklist

- **Authentication**
  - Successful student login
  - Successful librarian login  
  - Invalid login handling
  - Logout functionality

- **Book Operations**
  - Browse all books
  - Search functionality
  - Book reservation
  - Reservation cancellation

- **Admin Features**
  - Role-based access control
  - Add new books
  - Delete books
  - User management

- **System Reliability**
  - Data persistence after restart
  - Error handling
  - Input validation
  - Menu navigation

- **Edge Cases**
  - Reserve already reserved book
  - Cancel non-existent reservation
  - Invalid menu selections

### Expected Final State
After complete testing, the system should:
- Handle all user interactions without crashing
- Maintain data integrity across sessions
- Provide clear error messages
- Enforce role-based permissions
- Process reservations correctly

**All testing should be performed sequentially following the test cases above.**

### Screenshots

