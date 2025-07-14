<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ page import="javax.servlet.http.HttpSession" %>--%>
<%
    //    HttpSession session = request.getSession(false);
    String studentName = "";
//    if (session != null) {
//        studentName = (String) session.getAttribute("studentName");
//    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard - BC Wellness</title>
    <style>
        html, body {
            height: 100%;
            margin: 0;
            padding: 0;
            font-family: "Segoe UI", sans-serif;
            background-color: #f0f2f5;
            display: flex;
            flex-direction: column;
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

        /* Navbar */
        .navbar {
            background-color: #2196F3;
            padding: 15px 30px;
            color: white;
            display: flex;
            justify-content: space-between;
            align-items: center;
            position: relative;
        }

        .else-nav a {
            color: white;
            text-decoration: none;
            margin-left: 20px;
            font-weight: bold;
            padding: 8px 12px;
            border-radius: 4px;
            transition: background-color 0.2s ease-in-out;
        }

        .else-nav a:hover {
            background-color: rgba(255, 255, 255, 0.2);
        }

        .brand {
            font-weight: bold;
            font-size: 18px;
        }

        .user-dropdown {
            position: relative;
            cursor: pointer;
        }

        .dropdown-toggle {
            font-weight: bold;
        }

        .dropdown-menu {
            display: none;
            position: absolute;
            right: 0;
            top: 100%;
            background-color: white;
            box-shadow: 0 2px 6px rgba(0, 0, 0, 0.2);
            border-radius: 5px;
            overflow: hidden;
            z-index: 1000;
            min-width: 160px;
        }

        .dropdown-menu a,
        .dropdown-menu button {
            display: block;
            width: 100%;
            padding: 12px 20px;
            text-align: left;
            text-decoration: none;
            background: none;
            border: none;
            color: #333;
            font-size: 14px;
        }

        .dropdown-menu a:hover,
        .dropdown-menu button:hover {
            background-color: #f2f2f2;
        }


        .navbar form {
            display: inline;
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

        .main-content {
            flex: 1;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }

        /* Footer */
        .footer {
            background-color: #222;
            color: #bbb;
            text-align: center;
            padding: 20px;
            font-size: 14px;
        }
    </style>
</head>
<body>

<!-- Navbar -->
<div class="navbar">
    <div class="brand">BC Wellness System</div>
    <div class="user-dropdown">
        <div class="dropdown-toggle" onclick="toggleDropdown()">
            Welcome, <%= studentName %> ⏷
        </div>
        <div class="dropdown-menu" id="dropdownMenu">
            <a class="else-nav" href="index.jsp">Home</a>
            <form action="LogoutServlet" method="post">
                <button type="submit">Logout</button>
            </form>
        </div>
    </div>
</div>

<!-- Main Content Area -->
<main class="main-content">
    <div class="dashboard-container">
        <h2>Welcome, Student Name!</h2>
        <p>You have successfully logged in to the BC Wellness Management System.</p>
        <form action="LogoutServlet" method="post">
            <input type="submit" class="btn-logout" value="Logout" />
        </form>
    </div>
</main>

<!-- Footer -->
<div class="footer">
    © 2025 Belgium Campus | Student Wellness Management System
</div>

<script>
    function toggleDropdown() {
        const menu = document.getElementById("dropdownMenu");
        menu.style.display = (menu.style.display === "block") ? "none" : "block";
    }

    // Optional: close dropdown when clicking outside
    window.onclick = function(event) {
        const dropdown = document.getElementById("dropdownMenu");
        if (!event.target.matches('.dropdown-toggle')) {
            if (dropdown && dropdown.style.display === "block") {
                dropdown.style.display = "none";
            }
        }
    };
</script>

</body>
</html>
