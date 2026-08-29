<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Customer Information</title>
    <link rel="stylesheet" href="CSS/customerinfo.css">
</head>
<body>
<div class="customer-info-container">
    <h2>Customer Information</h2>

    <!-- 显示预订详情 -->
    <div class="booking-details">
        <p><strong>Hotel Name:</strong> <%= request.getParameter("hotelName") %></p>
        <p><strong>Room Number:</strong> <%= request.getParameter("roomNumber") %></p>
        <p><strong>Check-In Date:</strong> <%= request.getParameter("checkInDate") %></p>
        <p><strong>Check-Out Date:</strong> <%= request.getParameter("checkOutDate") %></p>
    </div>

    <!-- 客户信息填写表单 -->
    <form action="FinalizeBooking" method="post">
        <!-- 将预订信息以隐藏字段传递 -->
        <input type="hidden" name="hotelId" value="<%= request.getParameter("hotelId") %>">
        <input type="hidden" name="hotelName" value="<%= request.getParameter("hotelName") %>">
        <input type="hidden" name="roomNumber" value="<%= request.getParameter("roomNumber") %>">
        <input type="hidden" name="roomId" value="<%= request.getParameter("roomId") %>">
        <input type="hidden" name="checkInDate" value="<%= request.getParameter("checkInDate") %>">
        <input type="hidden" name="checkOutDate" value="<%= request.getParameter("checkOutDate") %>">

        <!-- 客户信息输入 -->
        <div class="form-group">
            <label for="idType">ID Type:</label>
            <select id="idType" name="idType" required>
                <option value="">--Select--</option>
                <option value="Driver_License">Driver License</option>
                <option value="SSN">SSN</option>
                <option value="Passport">Passport</option>
            </select>
        </div>
        <div class="form-group">
            <label for="customerName">Name:</label>
            <input type="text" id="customerName" name="customerName" required>
        </div>
        <div class="form-group">
            <label for="customerEmail">Email:</label>
            <input type="email" id="customerEmail" name="customerEmail" required>
        </div>
        <div class="form-group">
            <label for="customerIdNumber">ID:</label>
            <input type="text" id="customerIdNumber" name="customerIdNumber" required>
        </div>
        <div class="form-group">
            <label for="customerAddress">Address:</label>
            <input type="text" id="customerAddress" name="customerAddress" required>
        </div>
        <div class="form-group">
            <input type="submit" value="Submit Booking" class="submit-button">
        </div>
    </form>
</div>
</body>
</html>
