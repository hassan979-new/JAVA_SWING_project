/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class ConnexionSingleton {

    private static ConnexionSingleton instance;
    private final Connection connexion;

    private static final String URL = "jdbc:mysql://localhost:3306/bibliotheque";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private ConnexionSingleton() throws SQLException {
        this.connexion = DriverManager.getConnection(URL, USER, PASSWORD);
        this.connexion.setAutoCommit(true);
    }

    public static synchronized ConnexionSingleton getInstace() throws SQLException {
        if (instance == null || instance.connexion.isClosed()) {
            instance = new ConnexionSingleton();
        }
        return instance;
    }

    public Connection getConnection() {
        return connexion;
    }
}
