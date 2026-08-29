-- Speeds up queries that filter rooms by a hotel
CREATE INDEX idx_room_hotel_id ON Room(hotel_id);

-- Optimizes customer-related booking history queries. 
CREATE INDEX idx_booking_customer_id ON Booking(customer_id);

-- Helps quickly check room availability over a date range. 
CREATE INDEX idx_renting_room_dates ON Renting(room_id, start_date, end_date);

/*
-- Example Use-Cases
SELECT * FROM Room WHERE hotel_id = 5;

EXPLAIN SELECT * FROM Booking WHERE customer_id = 'CUST004';

SELECT * FROM Renting
WHERE room_id = 1
AND (start_date <= '2025-04-01' and end_date <= '2025-04-01');
*/
