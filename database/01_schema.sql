SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS Archive;
DROP TABLE IF EXISTS Renting;
DROP TABLE IF EXISTS Booking;
DROP TABLE IF EXISTS Room;
DROP TABLE IF EXISTS Employee;
DROP TABLE IF EXISTS Hotel;
DROP TABLE IF EXISTS Hotel_chain;
DROP TABLE IF EXISTS Customer;
SET FOREIGN_KEY_CHECKS = 1;

-- Hotel Chain Table
CREATE TABLE Hotel_chain (
    chain_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone_number VARCHAR(20) NOT NULL,
    central_office_address TEXT NOT NULL
)ENGINE=InnoDB;

-- Hotel Table
CREATE TABLE Hotel (
    hotel_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    category ENUM('1 star', '2 star', '3 star', '4 star', '5 star') NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    chain_id INT NOT NULL,
    address TEXT NOT NULL,
    FOREIGN KEY (chain_id) REFERENCES Hotel_chain(chain_id) ON DELETE CASCADE
)ENGINE=InnoDB;

-- Employee Table
CREATE TABLE Employee (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    role ENUM('Manager', 'Cleaner', 'Receptionist', 'Security', 'Chef', 'Waiter') NOT NULL,
    is_manager BOOLEAN DEFAULT FALSE,
    address TEXT NOT NULL,
    ssn_sin VARCHAR(50) UNIQUE NOT NULL,
    hotel_id INT NOT NULL,
    FOREIGN KEY (hotel_id) REFERENCES Hotel(hotel_id) ON DELETE CASCADE
)ENGINE=InnoDB;

-- Room Table
CREATE TABLE Room (
    room_id INT AUTO_INCREMENT PRIMARY KEY,
    room_number INT NOT NULL,
    price DECIMAL(10,2) NOT NULL CHECK (price > 0),
    capacity INT NOT NULL CHECK (capacity > 0),
    view ENUM('Sea', 'Mountain', 'City') NOT NULL,
    extendable BOOLEAN DEFAULT FALSE,
    amenities TEXT NOT NULL,
    issues TEXT,
    hotel_id INT NOT NULL,
    FOREIGN KEY (hotel_id) REFERENCES Hotel(hotel_id) ON DELETE CASCADE
)ENGINE=InnoDB;

-- Customer Table
CREATE TABLE Customer (
    customer_id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    id_type ENUM('Passport', 'Driver_License', 'SIN_SSN') NOT NULL,
    id_number VARCHAR(50) NOT NULL UNIQUE,
    registration_date DATE NOT NULL DEFAULT (CURRENT_DATE),
    address TEXT NOT NULL
) ENGINE=InnoDB;

-- Booking Table
CREATE TABLE Booking (
    booking_id INT AUTO_INCREMENT PRIMARY KEY,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    status ENUM('Pending', 'Confirmed', 'Cancelled') NOT NULL DEFAULT 'Pending',
    customer_id VARCHAR(50) NOT NULL,
    room_id INT NOT NULL,
    employee_id INT NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES Customer(customer_id) ON DELETE CASCADE,
    FOREIGN KEY (room_id) REFERENCES Room(room_id),
    FOREIGN KEY (employee_id) REFERENCES Employee(employee_id)
)ENGINE=InnoDB;

-- Renting Table
CREATE TABLE Renting (
    renting_id INT AUTO_INCREMENT PRIMARY KEY,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    status ENUM('Checked-in', 'Checked-out', 'Cancelled') NOT NULL DEFAULT 'Checked-in',
    customer_id VARCHAR(50) NOT NULL,
    room_id INT NOT NULL,
    booking_id INT NULL,
    employee_id INT NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES Customer(customer_id),
    FOREIGN KEY (room_id) REFERENCES Room(room_id),
    FOREIGN KEY (booking_id) REFERENCES Booking(booking_id),
    FOREIGN KEY (employee_id) REFERENCES Employee(employee_id)
)ENGINE=InnoDB;

-- Archive Table
CREATE TABLE Archive (
    archive_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id VARCHAR(50),
    room_id INT,
    booking_id INT,
    renting_id INT,
    FOREIGN KEY (customer_id) REFERENCES Customer(customer_id) ON DELETE SET NULL,
    FOREIGN KEY (room_id) REFERENCES Room(room_id) ON DELETE SET NULL,
    FOREIGN KEY (booking_id) REFERENCES Booking(booking_id) ON DELETE SET NULL,
    FOREIGN KEY (renting_id) REFERENCES Renting(renting_id) ON DELETE SET NULL
)ENGINE=InnoDB;
