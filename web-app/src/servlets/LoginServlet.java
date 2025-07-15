package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.AuthenticateUtils;
import utils.DBUtils;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
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

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Input validation
        if (email == null || password == null || email.trim().isEmpty() || password.trim().isEmpty()) {
            request.setAttribute("error", "Email and password are required.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        try {
            // Load JDBC driver
            Class.forName("org.postgresql.Driver");
            try (Connection conn = DBUtils.getConnection()) {
                String sql = "SELECT * FROM users WHERE email = ?";
                String hashedPassword = AuthenticateUtils.hashPassword(password);

                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, email);
                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        String storedHash = rs.getString("password");
                        if (AuthenticateUtils.hashPassword(password).equals(storedHash)) {
                            HttpSession session = request.getSession();
                            session.setAttribute("user", email);
                            session.setAttribute("studentName", rs.getString("name")); // if available
                            response.sendRedirect("dashboard.jsp");
                        } else {
                            request.setAttribute("error", "Invalid email or password.");
                            request.getRequestDispatcher("login.jsp").forward(request, response);
                        }
                    } else {
                        request.setAttribute("error", "Invalid email or password.");
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error: " + e.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
