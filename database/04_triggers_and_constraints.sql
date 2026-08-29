-- 2d: Triggers & Modifications (MySQL-Compatible)

-- Safely drop and recreate the foreign key constraint
#ALTER TABLE Room
#DROP FOREIGN KEY room_hotel_fk;

ALTER TABLE Room
ADD CONSTRAINT room_hotel_fk
FOREIGN KEY (hotel_id) REFERENCES Hotel(hotel_id) ON DELETE RESTRICT;

-- Set default status to 'Pending' for new bookings
ALTER TABLE Booking
MODIFY status ENUM('Pending', 'Confirmed', 'Cancelled') DEFAULT 'Pending';

-- Trigger 1: Insert into Renting when booking is confirmed
DROP TRIGGER IF EXISTS check_in_trigger;
DELIMITER $$

CREATE TRIGGER check_in_trigger
AFTER UPDATE ON Booking
FOR EACH ROW
BEGIN
    IF NEW.status = 'Confirmed' THEN
        INSERT INTO Renting (customer_id, room_id, start_date, end_date, status)
        VALUES (NEW.customer_id, NEW.room_id, NEW.start_date, NEW.end_date, 'Checked-in');
    END IF;
END$$

DELIMITER ;

-- Trigger 2: Prevent overlapping bookings
DROP TRIGGER IF EXISTS prevent_overlapping_bookings;
DELIMITER $$

CREATE TRIGGER prevent_overlapping_bookings
BEFORE INSERT ON Booking
FOR EACH ROW
BEGIN
    IF EXISTS (
        SELECT 1 FROM Booking
        WHERE room_id = NEW.room_id
        AND status = 'Confirmed'
        AND (
            NEW.start_date BETWEEN start_date AND end_date
            OR NEW.end_date BETWEEN start_date AND end_date
        )
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Room is already booked for the selected dates!';
    END IF;
END$$

DELIMITER ;
