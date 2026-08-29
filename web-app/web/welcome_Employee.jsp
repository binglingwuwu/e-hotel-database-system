<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Welcome, Employee</title>
  <link rel="stylesheet" href=CSS/welcome_Employee.css>
</head>
<body>
<div class="container">
  <!-- 欢迎和打印员工信息 -->
  <div class="welcome-message">
    <h2>Welcome, Employee!</h2>
  </div>

  <!-- 登录表单 -->
  <div class="login-form">
    <form action="EmployeeLogin" method="post">
      <div class="form-group">
        <label for="hotelId">Hotel ID:</label>
        <input type="text" id="hotelId" name="hotelId" placeholder="Enter Hotel ID" required>
      </div>
      <div class="form-group">
        <label for="employeeId">Employee ID:</label>
        <input type="text" id="employeeId" name="employeeId" placeholder="Enter Employee ID" required>
      </div>
      <div class="form-group">
        <input type="submit" value="Confirm Login" class="login-button">
      </div>
    </form>
  </div>
</div>
</body>
</html>
