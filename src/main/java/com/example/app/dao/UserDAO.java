package com.example.app.dao;

import com.example.app.connect.Connect;
import com.example.app.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;


import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public static boolean insertUser(User user) {
        // SQL statement without the id column, as it will be auto-incremented.
        String sql = "INSERT INTO userr (name, lastname, email, cin, password) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Connect.getConnection();
             // Specify that you want to retrieve the generated keys
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Set the parameter values from the User object
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getLastname());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getCin());
            pstmt.setString(5, user.getPassword());

            // Execute the insert operation
            int rowsInserted = pstmt.executeUpdate();

            // Check if the insert was successful
            if (rowsInserted > 0) {
                // Retrieve the generated key (id)
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int generatedId = rs.getInt(1);
                        // Optionally, set the generated id back to the User object
                        user.setId(generatedId);
                    }
                }
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean authenticate(String email, String password) throws Exception {
        try (Connection connection = Connect.getConnection()) {
            String query = "SELECT password FROM userr WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");

                // Compare the hashed password from the database with the hashed input password
                return hashPassword(password).equals(storedPassword);
            }
        }
        return false; // User does not exist or password is incorrect
    }
    public static String hashPassword(String password) throws Exception {
        java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(password.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
    public static ObservableList<User> getAllUsers() {
        List<User> users = new ArrayList<User>();

        String sql = "SELECT  *  FROM userr";

        try (Connection con = Connect.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String lastname = rs.getString("lastname");
                String email = rs.getString("email");
                String cin = rs.getString("cin");
                String password = rs.getString("password");

                users.add(new User(id,name,lastname,email,cin,password));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return FXCollections.observableArrayList(users);

    }
    public static void deleteUser(String email) {
        String sql = "DELETE FROM userr WHERE email = ?";
        try (Connection conn = Connect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updateUser(User user, int _id) {
        String sql = "UPDATE userr SET name = ?, lastname = ?,cin= ? , email = , password=? ? WHERE id = ?";
        try (Connection conn = Connect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getLastname());

            pstmt.setString(3, user.getCin());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5, user.getPassword());
            pstmt.setInt(6, _id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
