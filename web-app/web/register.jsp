<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <!-- 引入外部 CSS 文件（路径可根据实际项目结构调整） -->
    <link rel="stylesheet" href="CSS/register.css">
</head>
<body>
<div class="register-container">
    <h2>Register</h2>

    <!-- 如果有错误信息，就显示出来 -->
    <%
        String errorMsg = (String) request.getAttribute("error");
        if (errorMsg != null) {
    %>
    <div id="error-message">
        <%= errorMsg %>
    </div>
    <%
        }
    %>

    <!-- 提交表单到 /register（Servlet 或 Controller）进行注册处理 -->
    <form action="register" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>

        <label for="confirmPassword">Confirm Password:</label>
        <input type="password" id="confirmPassword" name="confirmPassword" required>

        <!-- 注册按钮 -->
        <input type="submit" value="Register" class="register-button">
    </form>

    <!-- 返回登录页面的链接，可选 -->
    <div class="back-to-login">
        <a href="index.jsp">Back to Login</a>
    </div>
</div>
</body>
</html>
