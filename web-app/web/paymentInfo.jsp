<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Payment Information</title>
    <link rel="stylesheet" href="CSS/paymentInfo.css">
</head>
<body>
<div class="payment-container">
    <h2>Payment Information</h2>
    <%
        // 从 request 域中获取 renting_id
        String rentingID = (String) request.getAttribute("renting_id");
    %>
    <form action="PaymentInfo" method="post">
        <!-- 将 renting_id 放入隐藏字段 -->
        <input type="hidden" name="renting_id" value="<%= rentingID %>" />
        <div class="form-group">
            <label for="paymentMethod">Payment Method:</label>
            <select id="paymentMethod" name="paymentMethod" required>
                <option value="">--Select--</option>
                <option value="Credit Card">Credit Card</option>
                <option value="Cash">Cash</option>
                <option value="Paypal">Check</option>
            </select>
        </div>
        <div class="form-group">
            <label for="amount">Payment Amount:</label>
            <input type="number" step="0.01" id="amount" name="amount" placeholder="Enter amount" required>
        </div>
        <div class="form-group">
            <input type="submit" value="Submit Payment" class="submit-button">
        </div>
    </form>
</div>
</body>
</html>
