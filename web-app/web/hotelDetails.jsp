<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="entity.RoomEntity" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hotel room details</title>
    <!-- 引入酒店详情页的样式（假设已有） -->
    <link rel="stylesheet" href="CSS/hoteldetails.css">
</head>
<body>
<div class="details-container">
    <h2>Hotel room details</h2>
    <!-- 显示传递过来的搜索条件 -->
    <div class="search-params">
        <p><strong>Check In:</strong> <%= request.getAttribute("checkInDate") %></p>
        <p><strong>Check Out:</strong> <%= request.getAttribute("checkOutDate") %></p>
        <p><strong>Room Capacity:</strong> <%= request.getAttribute("capacity") %></p>
        <p><strong>Max Price:</strong> <%= request.getAttribute("maxPrice") %></p>
    </div>
    <hr>
    <!-- 遍历房间列表 -->
    <div class="room-list">
        <%
            List roomList = (List) request.getAttribute("roomList");
            if (roomList != null) {
                for (int i = 0; i < roomList.size(); i++) {
                    RoomEntity room = (RoomEntity) roomList.get(i);
        %>
        <div class="room-item">
            <p><strong>Room number:</strong> <%= room.getNumber() %></p>
            <p><strong>Price:</strong> $<%= room.getPrice() %> per night</p>
            <p><strong>Capacity:</strong> <%= room.getCapacity() %> 人</p>
            <p><strong>View:</strong> <%= room.getView() %></p>
            <p><strong>Amenitions:</strong> <%= room.getAmenities() %></p>

            <!-- 预定按钮：点击后将预定信息提交到 CustomerInfoServlet -->
            <form action="CustomerInfo" method="post">
                <input type="hidden" name="hotelId" value="<%= request.getAttribute("hotelId") %>" />
                <input type="hidden" name="hotelName" value="<%= request.getAttribute("hotelName") %>" />
                <input type="hidden" name="roomNumber" value="<%= room.getNumber() %>" />
                <input type="hidden" name="roomId" value="<%= room.getR_id() %>" />
                <input type="hidden" name="checkInDate" value="<%= request.getAttribute("checkInDate") %>" />
                <input type="hidden" name="checkOutDate" value="<%= request.getAttribute("checkOutDate") %>" />
                <input type="hidden" name="capacity" value="<%= request.getAttribute("capacity") %>" />
                <input type="hidden" name="maxPrice" value="<%= request.getAttribute("maxPrice") %>" />
                <input type="hidden" name="roomPrice" value="<%= room.getPrice() %>" />
                <input type="submit" value="booking" class="book-button" />
            </form>
        </div>
        <%
            }
        } else {
        %>
        <p>No room information!</p>
        <%
            }
        %>
    </div>
</div>
</body>
</html>
