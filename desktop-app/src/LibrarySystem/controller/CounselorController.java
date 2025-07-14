package LibrarySystem.controller;

import LibrarySystem.model.Counselor;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;

public class CounselorController {

    public static void addCounselor(Counselor c) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "INSERT INTO Counselors (name, specialization, availability) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, c.getName());
            ps.setString(2, c.getSpecialization());
            ps.setString(3, c.getAvailability());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Counselor> getAllCounselors() {
        ArrayList<Counselor> list = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Counselors");

            while (rs.next()) {
                list.add(new Counselor(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("specialization"),
                        rs.getString("availability")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // TODO: updateCounselor(), deleteCounselor()
}
