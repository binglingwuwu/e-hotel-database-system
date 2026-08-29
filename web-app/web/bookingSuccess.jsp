<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Booking Success</title>
  <!-- 引入 bookingSuccess.css -->
  <link rel="stylesheet" href="CSS/bookingSuccess.css">
</head>
<body>
<div class="success-container">
  <h2>Booking Successful!</h2>
  <p><%= request.getAttribute("message") %></p>
  <a href="hotelSearch.jsp">Back to Hotel Search</a>
</div>
</body>
</html>
