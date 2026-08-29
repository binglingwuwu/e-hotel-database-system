<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, entity.Booking" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Booking Management</title>
    <link rel="stylesheet" href="CSS/bookingManagement.css">
    <script>
        function validateSearchForm() {
            // 获取四个字段的值，并去除首尾空格
            var roomNumber = document.getElementById("roomNumber").value.trim();
            var roomView = document.getElementById("roomView").value.trim();
            var custIdType = document.getElementById("custIdType").value.trim();
            var custIdNumber = document.getElementById("custIdNumber").value.trim();

            // 如果任一字段为空，则显示提示并阻止提交
            if (roomNumber === "" || roomView === "" || custIdType === "" || custIdNumber === "") {
                alert("All fields (Room Number, Room View, Customer ID Type, Customer ID Number) must be filled.");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<div class="container">
    <!-- 搜索区域 -->
    <div class="search-area">
        <h2>Search Bookings</h2>
        <!-- 在 form 标签中添加 onsubmit 校验 -->
        <form action="BookingManagement" method="post" onsubmit="return validateSearchForm();">
            <div class="form-group">
                <label for="roomNumber">Room Number:</label>
                <input type="text" id="roomNumber" name="roomNumber" value="<%= request.getParameter("roomNumber") != null ? request.getParameter("roomNumber") : "" %>">
            </div>
            <div class="form-group">
                <label for="roomView">Room View:</label>
                <select id="roomView" name="roomView">
                    <option value="">--Select--</option>
                    <option value="Mountain" <%= "Mountain".equals(request.getParameter("roomView")) ? "selected" : "" %>>Mountain</option>
                    <option value="Sea" <%= "Sea".equals(request.getParameter("roomView")) ? "selected" : "" %>>Sea</option>
                </select>
            </div>
            <!-- 客户身份证件类型 -->
            <div class="form-group">
                <label for="custIdType">Customer ID Type:</label>
                <select id="custIdType" name="custIdType">
                    <option value="">--Select--</option>
                    <option value="Driver_License" <%= "Driver_License".equals(request.getParameter("custIdType")) ? "selected" : "" %>>Driver License</option>
                    <option value="SSN" <%= "SSN".equals(request.getParameter("custIdType")) ? "selected" : "" %>>SSN</option>
                    <option value="Passport" <%= "Passport".equals(request.getParameter("custIdType")) ? "selected" : "" %>>Passport</option>
                </select>
            </div>
            <!-- 客户身份证件号码 -->
            <div class="form-group">
                <label for="custIdNumber">Customer ID Number:</label>
                <input type="text" id="custIdNumber" name="custIdNumber" value="<%= request.getParameter("custIdNumber") != null ? request.getParameter("custIdNumber") : "" %>">
            </div>
            <div class="form-group">
                <input type="submit" value="Search" class="search-button">
            </div>
        </form>
    </div>

    <!-- 搜索结果区域 -->
    <div class="results-area">
        <h2>Booking Results</h2>
        <%
            List bookingList = (List) request.getAttribute("bookingList");
            if (bookingList != null && !bookingList.isEmpty()) {
                for (int i = 0; i < bookingList.size(); i++) {
                    Booking record = (Booking) bookingList.get(i);
        %>
        <div class="booking-box">
            <div class="booking-info">
                <p><strong>Room Number:</strong> <%= record.getRoomNumber() %></p>
                <p><strong>Room Price:</strong> $<%= record.getRoomPrice() %></p>
                <p><strong>Room View:</strong> <%= record.getRoomView() %></p>
                <p><strong>Booking Period:</strong> <%= record.getStartDate() %> to <%= record.getEndDate() %></p>
                <p><strong>Customer Name:</strong> <%= record.getCustomerName() %></p>
                <p><strong>Customer Identity:</strong> <%= record.getCustomerID() %></p>
                <p><strong>Customer Address:</strong> <%= record.getCustomerAddress() %></p>
            </div>
            <div class="action-buttons">
                <!-- 表单：转换预定 -->
                <form action="UpdateBookingStatusServlet" method="post">
                    <input type="hidden" name="bookingId" value="<%= record.getBookingID() %>">
                    <input type="hidden" name="newStatus" value="Confirmed">
                    <input type="submit" value="Confirmed" class="Confirmed">
                </form>
                <!-- 表单：取消预定 -->
                <form action="UpdateBookingStatusServlet" method="post">
                    <input type="hidden" name="bookingId" value="<%= record.getBookingID() %>">
                    <input type="hidden" name="newStatus" value="Cancelled">
                    <input type="submit" value="Cancelled" class="Cancelled">
                </form>
            </div>
        </div>
        <%
            }
        } else {
        %>
        <p>No booking records found.</p>
        <%
            }
        %>
    </div>
</div>
</body>
</html>
