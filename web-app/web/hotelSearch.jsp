<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, entity.HotelEntity" %>
<%@ page import="db.ChainDB" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hotel Room Search</title>
    <!-- 引入 CSS 文件 -->
    <link rel="stylesheet" href="CSS/hotelsearch.css">
</head>
<body>

<!-- 搜索条件区域 -->
<div class="search-container">
    <h2>Search for Available Rooms</h2>
    <form method="post" action="hotelSearch">
        <!-- 起始日期 -->
        <div class="filter-group">
            <label for="startDate">Check-in Date:</label>
            <input type="date" id="startDate" name="startDate">
        </div>
        <!-- 结束日期 -->
        <div class="filter-group">
            <label for="endDate">Check-out Date:</label>
            <input type="date" id="endDate" name="endDate">
        </div>
        <!-- 房间容量 -->
        <div class="filter-group">
            <label for="capacity">Room Capacity:</label>
            <input type="number" id="capacity" name="capacity" min="1">
        </div>
        <!-- 地区 -->
        <div class="filter-group">
            <label for="region">Region:</label>
            <select id="region" name="region">
                <option value="">-- Select --</option>
                <option value="Shuntian">Shuntian</option>
                <option value="Yingtian">Yingtian</option>
                <option value="Guangzhou">Guangzhou</option>
                <!-- 可添加更多选项 -->
            </select>
        </div>
        <!-- 酒店连锁 -->
        <div class="filter-group">
            <label for="chain">Hotel Chain:</label>
            <select id="chain" name="chain">
                <option value="">-- Select --</option>
                <option value="The Eastern Depot">The Eastern Depot</option>
                <option value="The Western Depot">The Western Depot</option>
                <option value="Embroidered Uniform Guard">Embroidered Uniform Guard</option>
                <option value="Don de Dieu">Don de Dieu</option>
            </select>
        </div>
        <!-- 酒店类别（星级） -->
        <div class="filter-group">
            <label for="category">Hotel Category:</label>
            <select id="category" name="category">
                <option value="">-- Select --</option>
                <option value="1">1 Stars</option>
                <option value="2">2 Stars</option>
                <option value="3">3 Stars</option>
                <option value="4">4 Stars</option>
                <option value="5">5 Stars</option>
            </select>
        </div>
        <!-- 酒店中客房总数 -->
        <div class="filter-group">
            <label for="totalRooms">Total Rooms:</label>
            <input type="number" id="totalRooms" name="totalRooms" min="1">
        </div>
        <!-- 房间价格 -->
        <div class="filter-group">
            <label for="price">Max price (per night):</label>
            <input type="number" id="price" name="price" min="0">
        </div>
        <!-- 搜索按钮 -->
        <div class="filter-group">
            <input type="submit" value="Search" class="search-button">
        </div>
    </form>
</div>

<!-- 搜索结果区域 -->
<div class="result-container">
    <%
        // 获取可用房间数（如果有）
        Integer availableRooms = (Integer) request.getAttribute("availableRooms");
        if (availableRooms != null) {
    %>
    <p class="available-info">
        Total available rooms in selected region: <strong><%= availableRooms %></strong>
    </p>
    <%
        }
        // 获取酒店列表（如果有）
        List hotelList = (List) request.getAttribute("hotelList");
        ChainDB chainDB = new ChainDB();
        if (hotelList != null) {
            for (int i = 0; i < hotelList.size(); i++) {
                HotelEntity hotel = (HotelEntity) hotelList.get(i);
    %>
    <div class="hotel-item">
        <div class="hotel-header">
            <span class="hotel-name"><%= hotel.getName() %></span>
            <span class="hotel-star"><%= hotel.getStar() %> Stars</span>
        </div>
        <div class="hotel-info">
            <p><strong>Phone:</strong> <%= hotel.getPhone() %></p>
            <p><strong>Chain:</strong> <%= hotel.getChain_name() %></p>
            <p><strong>Address:</strong> <%= hotel.getAddress() %></p>
            <p><strong>Email:</strong> <%= hotel.getEmail() %></p>
            <p><strong>Available Rooms:</strong> <%= hotel.getAvailableRooms() %></p>
        </div>

        <%
            String checkInDate = request.getParameter("startDate");
            String checkOutDate = request.getParameter("endDate");
            String capacity = request.getParameter("capacity");
            String maxPrice = request.getParameter("price");
            // ...


        %>

        <a class="hotel-link"
           href="hotelDetails?hotelId=<%= hotel.getId() %>
       &hotelName=<%= hotel.getName() %>
       &checkInDate=<%= checkInDate %>
       &checkOutDate=<%= checkOutDate %>
       &maxPrice=<%= maxPrice %>
       &capacity=<%= capacity %>">
            View Details
        </a>
    </div>
    <%
            }
        }
    %>
</div>

</body>
</html>
