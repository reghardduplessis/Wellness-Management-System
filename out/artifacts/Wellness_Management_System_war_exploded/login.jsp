<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login - BC Wellness</title>
    <style>
        body { font-family: Arial; padding: 20px; }
        form { width: 300px; margin: auto; background: #f9f9f9; padding: 20px; border-radius: 10px; }
        input { width: 100%; padding: 8px; margin: 10px 0; }
        .btn { background: #4CAF50; color: white; border: none; cursor: pointer; }
        .btn:hover { background: #45a049; }
        .error { color: red; text-align: center; }
    </style>
</head>
<body>
<h2 style="text-align:center;">Student Login</h2>

<%--<% String error = (String) request.getAttribute("errorMessage"); %>--%>
<%--<% if (error != null) { %>--%>
<%--<div class="error"><%= error %></div>--%>
<%--<% } %>--%>

<form action="LoginServlet" method="post">
    <label for="email">Email:</label>
    <input type="email" name="email" required />

    <label for="password">Password:</label>
    <input type="password" name="password" required />

    <input type="submit" value="Login" class="btn" />
</form>

<p style="text-align:center;">
    Don't have an account? <a href="register.jsp">Register here</a>
</p>
</body>
</html>