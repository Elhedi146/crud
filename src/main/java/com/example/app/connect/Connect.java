package com.example.app.connect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Connect {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/users"; // Modifier selon votre configuration
    private static final String USER = "root"; // Votre utilisateur
    private static final String PASSWORD = ""; // Votre mot de passe

    public static Connection getConnection() {
        try {
            System.out.println("Connection Established" );
            return DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (SQLException e) {
            System.err.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
            return null;
        }
    }
}
