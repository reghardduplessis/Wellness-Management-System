<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Dashboard - BC Wellness</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f4f6f9;
            padding: 50px;
        }
        .dashboard-container {
            width: 500px;
            margin: 0 auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.15);
            text-align: center;
        }
        h2 {
            margin-bottom: 20px;
        }
        .btn-logout {
            padding: 10px 20px;
            background: #f44336;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .btn-logout:hover {
            background: #d32f2f;
        }
    </style>
</head>
<body>

<div class="dashboard-container">
    <h2>Welcome, Student Name!</h2>
    <p>You have successfully logged in to the BC Wellness Management System.</p>

    <form action="LogoutServlet" method="post">
        <input type="submit" class="btn-logout" value="Logout" />
    </form>
</div>

</body>
</html>
