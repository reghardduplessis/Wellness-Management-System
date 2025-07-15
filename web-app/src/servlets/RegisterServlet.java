package servlets;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.AuthenticateUtils;
import utils.DBUtils;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final String CONFIG_PATH = "/WEB-INF/config.properties";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Load configuration
        try {
            Connection conn = DBUtils.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String student_number = request.getParameter("student_number");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String name  = request.getParameter("name");
        String surname = request.getParameter("surname");

        // Input validation
        if (email == null || password == null || phone == null || student_number == null ||name == null ||surname == null ||
                email.trim().isEmpty() || password.trim().isEmpty() || phone.trim().isEmpty() || student_number.trim().isEmpty()
                || name.trim().isEmpty()|| surname.trim().isEmpty()) {
            request.setAttribute("error", "All fields are required.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        if (!AuthenticateUtils.isValidEmail(email)) {
            request.setAttribute("error", "Invalid email format.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        if (!AuthenticateUtils.isStrongPassword(password)) {
            request.setAttribute("error", "Password must include 1 uppercase, 1 lowercase, 1 number, and 1 special character.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        String hashedPassword = AuthenticateUtils.hashPassword(password);


        try {
            // Load JDBC driver
            Class.forName("org.postgresql.Driver");
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                String sql = "INSERT INTO users (student_number,email, password, phone,name,surname) VALUES (?, ?, ?, ?, ?, ?,)";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, student_number);
                    stmt.setString(2, email);
                    stmt.setString(3, password); // Note: Use hashed passwords in production
                    stmt.setString(4, phone);
                    stmt.setString(5, name);
                    stmt.setString(6, surname);
            try (Connection conn = DBUtils.getConnection()) {
                // Check for existing email
                String checkSql = "SELECT COUNT(*) FROM users WHERE email = ?";
                try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                    checkStmt.setString(1, email);
                    ResultSet rs = checkStmt.executeQuery();
                    if (rs.next() && rs.getInt(1) > 0) {
                        request.setAttribute("error", "Email already exists.");
                        request.getRequestDispatcher("register.jsp").forward(request, response);
                        return;
                    }
                }

                // Insert new user
                String sql = "INSERT INTO users (email, password, phone) VALUES (?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, email);
                    stmt.setString(2, hashedPassword);
                    stmt.setString(3, phone);
                    int rows = stmt.executeUpdate();

                    if (rows > 0) {
                        response.sendRedirect("login.jsp?success=Registration successful. Please log in.");
                    } else {
                        request.setAttribute("error", "Registration failed.");
                        request.getRequestDispatcher("register.jsp").forward(request, response);
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error: " + e.getMessage());
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
