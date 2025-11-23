
## Project Statement

Traditional library management systems dont have online sites to show students if books are available to borrow or not. Students face difficulties in checking book availability, making reservations, and tracking their borrowing history. This leads to poor resource utilization, frustrated users, and increased administrative workload. There is a critical need for an automated, digital solution that can streamline library operations, provide real-time information access, and enhance the overall user experience for both students and library staff.

## Scope of the Project
The Library Management System is a comprehensive console-based application designed to help students see book availability digitally from their dorms and reserve books. The project scope encompasses:

#### Core System Features
- **User Management System**: Complete authentication and authorization system with role-based access control (Student and Librarian roles)
- **Digital Book Catalog**: Comprehensive inventory management system with real-time availability tracking and search functionality
- **Reservation Management**: Automated booking system with intelligent expiry handling and conflict resolution
- **Administrative Dashboard**: Full-featured management interface for librarians to oversee all library operations

#### Technical Scope
- **Console-Based Interface**: 100% Java console application with intuitive menu-driven navigation
- **Database Integration**: Persistent data storage using H2 database with Spring Data JPA
- **Business Logic**: Automated reservation expiry system with scheduled cleanup tasks
- **Input Validation**: Comprehensive error handling and input validation mechanisms
- **Data Persistence**: Reliable data storage ensuring information persists between application sessions

#### Functional Boundaries
- **In Scope**: User authentication, book CRUD operations, reservation management, search functionality, administrative controls
- **Out of Scope**: Physical book tracking, payment processing, email notifications, mobile application, web interface

## Target Users

#### Students
- **Demographics**: University students aged 18-25, technically proficient
- **Needs**: Easy access to book availability information, simple reservation process, personal reservation tracking
- **Usage Patterns**: Frequent access during academic periods, mobile-friendly preferences, quick transaction needs
- **Pain Points**: Uncertainty about book availability, complicated reservation processes, lack of real-time information

#### Librarians
- **Demographics**: Library staff and administrators, varying technical proficiency
- **Needs**: Comprehensive system oversight, efficient management tools, reporting capabilities, user management
- **Usage Patterns**: Daily system interaction, multiple simultaneous operations, data maintenance tasks
- **Pain Points**: Manual record keeping, difficulty tracking reservations, time-consuming administrative tasks

#### User Characteristics

| Aspect | Students | Librarians |
|--------|----------|------------|
| **Technical Proficiency** | High | Moderate to High |
| **Frequency of Use** | Periodic (during academic needs) | Daily (continuous operation) |
| **Primary Goals** | Quick book access and reservation | Efficient library management |
| **System Requirements** | Simple, fast interface | Comprehensive, detailed interface |

## High-Level Features

#### 1. Authentication & Authorization System
- **Role-Based Access Control**: Distinct permissions for Students and Librarians
- **Secure Login Mechanism**: Username/password authentication with session management
- **Automatic Session Handling**: Secure logout and session timeout features
- **User Profile Management**: Account creation and management capabilities

#### 2. Book Management Module
- **Complete CRUD Operations**: Create, Read, Update, Delete functionality for book inventory
- **Advanced Search System**: Real-time search across title, author, and ISBN fields
- **Availability Tracking**: Live status updates and reservation conflict prevention
- **Inventory Management**: Comprehensive book catalog with detailed metadata storage

#### 3. Reservation Management System
- **One-Click Reservation**: Simplified booking process for available books
- **Smart Expiry System**: Automatic 1-hour reservation expiry with background cleanup
- **Status Tracking**: Real-time monitoring of reservation states (Active, Expired, Cancelled)
- **Conflict Resolution**: Intelligent handling of overlapping reservation requests

#### 4. Administrative Control Panel
- **User Management**: Complete student account administration and management
- **System Monitoring**: Real-time overview of all library operations and statistics
- **Data Maintenance**: Tools for system cleanup and data integrity management
- **Reporting Features**: Basic reporting on library usage and reservation patterns

#### 5. User Experience Features
- **Console-Based Interface**: Clean, intuitive command-line interface accessible from any system
- **Responsive Design**: Optimized for various terminal sizes and configurations
- **Interactive Menus**: Hierarchical menu system with clear navigation paths
- **Immediate Feedback**: Real-time response to user actions with clear status messages

#### 6. System Management Features
- **Automated Maintenance**: Scheduled tasks for system cleanup and optimization
- **Error Handling**: Comprehensive exception handling with user-friendly error messages
- **Data Persistence**: Reliable file-based storage ensuring no data loss between sessions
- **Performance Optimization**: Efficient database queries and resource management

#### 7. Technical Architecture
- **Spring Boot Framework**: Enterprise-grade application framework ensuring reliability
- **H2 Database Integration**: Lightweight, file-based database solution
- **Maven Build System**: Standardized dependency management and build process
- **Java-based Console**: Platform-independent console application deployment

#### 8. Security & Reliability
- **Input Validation**: Protection against invalid data and potential security issues
- **Data Integrity**: Referential integrity maintenance and constraint enforcement
- **Error Recovery**: Graceful handling of exceptional conditions and system errors
- **Session Security**: Proper session management and access control enforcement
