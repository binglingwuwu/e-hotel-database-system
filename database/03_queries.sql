# 2c Database queries

#Q1 Average Room Price Per Hotel

SELECT h.hotel_id, h.name AS hotel_name, 
       ROUND(AVG(r.price), 2) AS avg_room_price
FROM Hotel h
JOIN Room r ON h.hotel_id = r.hotel_id
GROUP BY h.hotel_id, h.name
ORDER BY avg_room_price DESC;

#Q2 Number of Available Rooms Per Area

SELECT h.hotel_id, h.name AS hotel_name, COUNT(r.room_id) AS available_rooms
FROM Hotel h
JOIN Room r ON h.hotel_id = r.hotel_id
LEFT JOIN Booking b ON r.room_id = b.room_id AND b.status = 'Confirmed'
WHERE b.room_id IS NULL  -- Room is not booked
GROUP BY h.hotel_id, h.name
ORDER BY available_rooms DESC;


# Hotels with Above-Average Room Prices
SELECT h.hotel_id, h.name AS hotel_name, 
       ROUND(AVG(r.price), 2) AS avg_price
FROM Hotel h
JOIN Room r ON h.hotel_id = r.hotel_id
GROUP BY h.hotel_id, h.name
HAVING AVG(r.price) > (SELECT AVG(price) FROM Room);

#Q4 Customers Who Have Booked More Than 3 Times

SELECT customer_id, name, total_bookings
FROM (
    SELECT c.customer_id, c.name, COUNT(b.booking_id) AS total_bookings
    FROM Customer c
    JOIN Booking b ON c.customer_id = b.customer_id
    GROUP BY c.customer_id, c.name
) AS temp
WHERE total_bookings > 3;

#Q5 Most Frequently Booked Rooms

SELECT r.room_id, h.name AS hotel_name, COUNT(b.booking_id) AS total_bookings
FROM Room r
JOIN Hotel h ON r.hotel_id = h.hotel_id
JOIN Booking b ON r.room_id = b.room_id
GROUP BY r.room_id, h.name
ORDER BY total_bookings DESC
LIMIT 5;


