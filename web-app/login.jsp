<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login - BC Wellness System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f0f0f0;
            padding: 50px;
        }
        .login-container {
            width: 350px;
            margin: 0 auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.2);
        }
        h2 {
            text-align: center;
        }
        input[type="email"], input[type="password"] {
            width: 100%;
            padding: 10px;
            margin: 8px 0 15px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .btn {
            width: 100%;
            padding: 10px;
            background: #2196F3;
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
            color: red;
        }
    </style>
</head>
<body>

<div class="login-container">
    <h2>Student Login</h2>

    <form action="LoginServlet" method="post">
        <label for="email">Email:</label>
        <input type="email" name="email" required />

        <label for="password">Password:</label>
        <input type="password" name="password" required />

        <input type="submit" value="Login" class="btn" />
    </form>

    <p style="text-align:center; margin-top: 10px;">
        Don't have an account? <a href="register.jsp">Register here</a>
    </p>
</div>

</body>
</html>

