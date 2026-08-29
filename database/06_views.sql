-- the number of available rooms per area
CREATE VIEW Available_Rooms_Per_Area AS
SELECT 
    h.address AS area,
    COUNT(r.room_id) AS available_rooms
FROM Room r
JOIN Hotel h ON r.hotel_id = h.hotel_id
WHERE r.room_id NOT IN (
    SELECT room_id FROM Renting 
    WHERE status = 'Checked-in'
)
GROUP BY h.address;

-- Aggregated Capacity of All Rooms of a Specific Hotel
CREATE VIEW Hotel_Room_Capacity AS
SELECT 
    h.hotel_id,
    h.name AS hotel_name,
    SUM(r.capacity) AS total_capacity
FROM Hotel h
JOIN Room r ON h.hotel_id = r.hotel_id
GROUP BY h.hotel_id, h.name;
