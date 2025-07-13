<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ page import="javax.servlet.http.HttpSession" %>--%>
<%
//    HttpSession session = request.getSession(false);
    String studentName = "Steve";
//    if (session != null) {
//        studentName = (String) session.getAttribute("studentName");
//    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>BC Student Wellness Management System</title>
    <style>
        body {
            font-family: "Segoe UI", sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f0f2f5;
            color: #333;
        }

        /* Navbar */
        .navbar {
            background-color: #2196F3;
            padding: 15px 30px;
            color: white;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .navbar a {
            color: white;
            text-decoration: none;
            margin-left: 20px;
            font-weight: bold;
        }

        .navbar form {
            display: inline;
        }

        /* Header */
        .header {
            background-color: #ffffff;
            padding: 60px 30px 40px 30px;
            text-align: center;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }

        .header h1 {
            font-size: 36px;
            margin-bottom: 10px;
            color: #2196F3;
        }

        .header p {
            font-size: 18px;
            color: #555;
            max-width: 700px;
            margin: 0 auto;
        }

        /* Content */
        .content {
            text-align: center;
            padding: 40px 20px;
        }

        .btn {
            padding: 12px 25px;
            margin: 10px;
            background-color: #2196F3;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            transition: background 0.3s ease;
        }

        .btn:hover {
            background-color: #45a049;
        }

        /* Footer */
        .footer {
            background-color: #222;
            color: #bbb;
            text-align: center;
            padding: 20px;
            font-size: 14px;
            position: relative;
            bottom: 0;
            width: 100%;
            margin-top: 60px;
        }
    </style>
</head>
<body>

<!-- Navbar -->
<div class="navbar">
    <div><strong>BC Wellness System</strong></div>
    <div>
        <% if (studentName != null) { %>
        <span>Welcome, <%= studentName %></span>
        <a href="dashboard.jsp">Dashboard</a>
        <form action="LogoutServlet" method="post" style="display:inline;">
            <input type="submit" value="Logout" style="background:none;border:none;color:white;cursor:pointer;" />
        </form>
        <% } else { %>
        <a href="login.jsp">Login</a>
        <a href="register.jsp">Register</a>
        <% } %>
    </div>
</div>

<!-- Header Section -->
<div class="header">
    <h1>Welcome to the BC Student Wellness Program</h1>
    <p>
        Our mission is to support your well-being throughout your academic journey.
        Whether you're scheduling a counseling session, sharing feedback, or seeking support —
        this platform is here to connect you with the help you need.
    </p>
</div>

<!-- Main Content -->
<div class="content">
    <% if (studentName == null) { %>
    <a href="login.jsp"><button class="btn">Login</button></a>
    <a href="register.jsp"><button class="btn">Register</button></a>
    <% } else { %>
    <a href="dashboard.jsp"><button class="btn">Go to Dashboard</button></a>
    <% } %>
</div>

<!-- Footer -->
<div class="footer">
    © 2025 Belgium Campus | Student Wellness Management System
</div>

</body>
</html>
