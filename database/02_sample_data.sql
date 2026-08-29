
-- 2b

-- Insert Hotel Chains
INSERT INTO Hotel_chain (name, email, phone_number, central_office_address) VALUES
('HotelA', 'contact@HotelA.com', '111-111-1111', '123 Main St, Toronto, Canada'),
('HotelB', 'contact@HotelB.com', '222-222-2222', '456 King St, Vancouver, Canada'),
('HotelC', 'contact@HotelC.com', '333-333-3333', '789 Broadway, New York, USA'),
('HotelD', 'contact@HotelD.com', '444-444-4444', '101 Michigan Ave, Chicago, USA'),
('HotelE', 'contact@HotelE.com', '555-555-5555', '202 Ocean Dr, Miami, USA');

-- Insert Hotels
-- Assuming chain IDs are assigned sequentially starting from 1
INSERT INTO Hotel (name, category, phone_number, email, chain_id, address)
SELECT 
  CONCAT('Hotel', c.name, h.hotel_number) AS name,
  CASE 
    WHEN h.hotel_number % 3 = 1 THEN '3 star'
    WHEN h.hotel_number % 3 = 2 THEN '4 star'
    ELSE '5 star'
  END AS category,
  CONCAT(c.phone_prefix, '-', LPAD(h.hotel_number, 3, '0'), '-0000') AS phone_number,
  CONCAT('contact@Hotel', c.name, h.hotel_number, '.com') AS email,
  c.chain_id,
  CONCAT(h.hotel_number, ' Main St, ', c.city, ', ', c.country) AS address
FROM 
  (SELECT chain_id, name, 
          CASE 
            WHEN name = 'HotelA' THEN 'Toronto'
            WHEN name = 'HotelB' THEN 'Vancouver'
            WHEN name = 'HotelC' THEN 'New York'
            WHEN name = 'HotelD' THEN 'Chicago'
            ELSE 'Miami'
          END AS city,
          CASE 
            WHEN name = 'HotelA' THEN 'Canada'
            WHEN name = 'HotelB' THEN 'Canada'
            ELSE 'USA'
          END AS country,
          CASE 
            WHEN name = 'HotelA' THEN '111'
            WHEN name = 'HotelB' THEN '222'
            WHEN name = 'HotelC' THEN '333'
            WHEN name = 'HotelD' THEN '444'
            ELSE '555'
          END AS phone_prefix
   FROM Hotel_chain) c
JOIN 
  (SELECT 1 AS hotel_number UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL 
   SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8) h
ON 1=1;

-- Insert Managers
INSERT INTO Employee (name, role, is_manager, address, ssn_sin, hotel_id)
SELECT 
    CONCAT('Manager ', h.name),
    'Manager',
    TRUE,
    CONCAT(h.hotel_id, ' Main St, City'),
    CONCAT('SSN', LPAD(h.hotel_id, 4, '0')),
    h.hotel_id
FROM Hotel h
WHERE h.hotel_id <= 40;

-- other employees
INSERT INTO Employee (name, role, is_manager, address, ssn_sin, hotel_id)
VALUES 
('Receptionist A', 'Receptionist', FALSE, '12 Front St, Toronto', 'SSN9991', 1),
('Cleaner B', 'Cleaner', FALSE, '33 Side St, Vancouver', 'SSN9992', 2);

-- Insert Rooms
-- Assuming room numbers start from 101 and increment by 1
INSERT INTO Room (room_number, price, capacity, view, extendable, amenities, issues, hotel_id)
SELECT 
  100 + r.room_number AS room_number,
  CASE 
    WHEN r.room_number <= 2 THEN 100 + FLOOR(RAND() * 50)
    WHEN r.room_number <= 4 THEN 150 + FLOOR(RAND() * 50)
    WHEN r.room_number <= 6 THEN 200 + FLOOR(RAND() * 50)
    WHEN r.room_number <= 8 THEN 250 + FLOOR(RAND() * 50)
    ELSE 300 + FLOOR(RAND() * 50)
  END AS price,
  CASE 
    WHEN r.room_number <= 2 THEN 1
    WHEN r.room_number <= 4 THEN 2
    WHEN r.room_number <= 6 THEN 3
    WHEN r.room_number <= 8 THEN 4
    ELSE 5
  END AS capacity,
  CASE 
    WHEN r.room_number % 3 = 1 THEN 'Sea'
    WHEN r.room_number % 3 = 2 THEN 'Mountain'
    ELSE 'City'
  END AS view,
  r.room_number % 2 = 0 AS extendable,
  'WiFi, TV, Air Conditioning' AS amenities,
  NULL AS issues,
  h.hotel_id
FROM Hotel h
JOIN 
  (SELECT 1 AS room_number UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL 
   SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 UNION ALL 
   SELECT 10) r
ON 1=1;

-- rooms with issues
UPDATE Room
SET issues = 'The lamp is broken '
WHERE room_id = '233';

UPDATE Room
SET issues = 'TV is broken '
WHERE room_id = '123';

UPDATE Room
SET issues = 'The lamp is broken '
WHERE room_id = '57';

-- Insert Customers
INSERT INTO Customer (customer_id, name, id_type, id_number, registration_date, address) VALUES
('CUST001', 'John Doe', 'Passport', 'P12345678', '2024-01-01', '123 Maple St, Toronto, Canada'),
('CUST002', 'Jane Smith', 'Driver_License', 'D87654321', '2024-01-02', '456 Oak St, Vancouver, Canada'),
('CUST003', 'Michael Brown', 'SIN_SSN', 'S23456789', '2024-01-03', '789 Pine St, New York, USA'),
('CUST004', 'Emily Davis', 'Passport', 'P34567890', '2024-01-04', '101 Birch St, Chicago, USA'),
('CUST005', 'David Wilson', 'Driver_License', 'D45678901', '2024-01-05', '202 Cedar St, Miami, USA');

-- Insert Bookings
INSERT INTO Booking (start_date, end_date, status, customer_id, room_id, employee_id) VALUES
('2025-03-01', '2025-03-05', 'Confirmed', 'CUST003', 1, 2), 
('2025-04-10', '2025-04-15', 'Pending', 'CUST004', 6, 3),
('2025-04-10', '2025-04-15', 'Pending', 'CUST005', 1, 2);

-- Insert Bookings (4 each for CUST001 and CUST002)
INSERT INTO Booking (start_date, end_date, status, customer_id, room_id, employee_id) VALUES

-- Bookings for CUST001
('2025-03-01', '2025-03-05', 'Confirmed', 'CUST001', 1, 2),   -- Booking ID 1
('2025-04-01', '2025-04-05', 'Confirmed', 'CUST001', 2, 2),   -- Booking ID 2
('2025-05-01', '2025-05-05', 'Pending',   'CUST001', 3, 2),   -- Booking ID 3
('2025-06-01', '2025-06-05', 'Confirmed', 'CUST001', 4, 2),   -- Booking ID 4

-- Bookings for CUST002
('2025-04-10', '2025-04-15', 'Pending',   'CUST002', 6, 3),   -- Booking ID 5
('2025-05-10', '2025-05-15', 'Confirmed', 'CUST002', 7, 3),   -- Booking ID 6
('2025-06-10', '2025-06-15', 'Confirmed', 'CUST002', 8, 3),   -- Booking ID 7
('2025-07-10', '2025-07-15', 'Pending',   'CUST002', 9, 3);   -- Booking ID 8


-- Insert Rentings
-- Booking-based renting
INSERT INTO Renting (start_date, end_date, status, customer_id, room_id, booking_id, employee_id) VALUES
('2025-03-01', '2025-03-05', 'Checked-out', 'CUST001', 1, 1, 2),
('2025-04-10', '2025-04-15', 'Checked-in', 'CUST002', 6, 2, 3),

-- Direct renting (no booking)
('2025-03-06', '2025-03-08', 'Checked-out', 'CUST003', 10, NULL, 2),
('2025-03-09', '2025-03-11', 'Checked-out', 'CUST004', 11, NULL, 2),
('2025-04-12', '2025-04-14', 'Checked-in', 'CUST005', 12, NULL, 3);

-- Archive Records
INSERT INTO Archive (customer_id, room_id, booking_id, renting_id) VALUES
('CUST001', 1, 1, 1), -- Archived booking where customer still exists
(NULL, 2, NULL, 2);   -- Archived renting where room or customer may have been deleted

