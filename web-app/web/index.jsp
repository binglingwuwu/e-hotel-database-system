<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <!-- 引入外部 CSS 文件 -->
    <link rel="stylesheet" href="CSS/login.css">
</head>
<body>
<div class="login-container">
    <h2>Login</h2>

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

    <form action="login" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>

        <!-- 使用 Flex 布局让两个按钮并排显示 -->
        <div class="button-row">
            <!-- 登录按钮 -->
            <input type="submit" value="Login" class="login-button">
            <!-- 注册按钮（跳转到注册页面 register.jsp） -->
            <a href="register.jsp" class="register-button">Register</a>
        </div>
    </form>
</div>
</body>
</html>
