Key Components

Entities:
->Users 
->Train
->Ticket


Services
->UserBookingService
->TicketService

User: Represents users with fields for username, password, hashed password, user ID, and tickets booked.
Train: Represents trains, including train ID and station list.
Ticket: Represents booking details including train, seat number, and booking date.
Services:

UserBookingService: Manages user operations such as sign-up, login, fetching bookings, etc.
Utilities:

UserServiceUtil: Provides utility functions for password hashing and verification.
JSON Database
users.json: Stores user data.
train.json: Stores train data.
These files are located under src/main/java/org.example/localDb.

How to Run
Clone the Repository:

bash
Copy code
git clone https://github.com/yourusername/irctc-backend.git
cd irctc-backend





Features


User Sign-Up: Secure user registration with password hashing.
User Login: Authentication for users to access their account.

Train Search: Search for available trains.
Booking Management: Book, view, and cancel train bookings.
Configuration
File Paths: Database paths are configured in the code for simplicity. Update these paths in UserBookingService and other relevant classes if necessary.
