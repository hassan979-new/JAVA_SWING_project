/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import util.ConnexionSingleton;

/**
 *
 * @author HP
 */
public class GraphDao {

    public Map<Integer, Integer> getEmpruntMois() {
        Map<Integer, Integer> data = new HashMap<>();

        String req = "SELECT MONTH(dateEmprunt) AS m, COUNT(*) AS total FROM emprunt GROUP BY MONTH(dateEmprunt) ORDER BY m";
        try (PreparedStatement ps = ConnexionSingleton.getInstace().getConnection().prepareStatement(req)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int mois = rs.getInt("m");
                int total = rs.getInt("total");
                data.put(mois, total);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return data;
    }
}
