<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register - BC Wellness System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #eef1f5;
            padding: 50px;
        }
        .register-container {
            width: 400px;
            margin: 0 auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.2);
        }
        h2 {
            text-align: center;
        }
        input[type="text"],
        input[type="email"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin: 8px 0 15px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .btn {
            width: 100%;
            padding: 10px;
            background: #0b7dda;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .btn:hover {
            background: #0b7dda;
        }
        .message {
            text-align: center;
            color: #0b7dda;
        }
        .error {
            text-align: center;
            color: red;
        }
    </style>
</head>
<body>

<div class="register-container">
    <h2>Create Student Account</h2>

<%--    &lt;%&ndash; Optional: show success or error message from servlet &ndash;%&gt;--%>
<%--    <%--%>
<%--        String message = (String) request.getAttribute("message");--%>
<%--        String error = (String) request.getAttribute("errorMessage");--%>
<%--        if (message != null) {--%>
<%--    %>--%>
<%--    <div class="message"><%= message %></div>--%>
<%--    <% } else if (error != null) { %>--%>
<%--    <div class="error"><%= error %></div>--%>
<%--    <% } %>--%>

    <form action="RegisterServlet" method="post">
        <label for="studentNumber">Student Number:</label>
        <input type="text" name="studentNumber" required />

        <label for="name">First Name:</label>
        <input type="text" name="name" required />

        <label for="surname">Surname:</label>
        <input type="text" name="surname" required />

        <label for="email">Email:</label>
        <input type="email" name="email" required />

        <label for="phone">Phone:</label>
        <input type="text" name="phone" pattern="[0-9]{10}" required title="Enter a 10-digit phone number" />

        <label for="password">Password:</label>
        <input type="password" name="password" required minlength="6" />

        <input type="submit" value="Register" class="btn" />
    </form>

    <p style="text-align:center; margin-top: 10px;">
        Already have an account? <a href="login.jsp">Login here</a>
    </p>
</div>

</body>
</html>
