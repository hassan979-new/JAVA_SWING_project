/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import util.ConnexionSingleton;

/**
 *
 * @author HP
 */
public class GraphDao {

    public Map<String, Integer> getEmpruntMois() {
        Map<String, Integer> data = new HashMap<>();

        String req = "SELECT YEAR(dateEmprunt) AS y, MONTH(dateEmprunt) AS m, COUNT(*) AS total "
               + "FROM emprunt GROUP BY YEAR(dateEmprunt), MONTH(dateEmprunt) ORDER BY y, m";
        try (PreparedStatement ps = ConnexionSingleton.getInstace().getConnection().prepareStatement(req)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int annee = rs.getInt("y");
                int mois = rs.getInt("m");
                int total = rs.getInt("total");
                String moisString = Month.of(mois).getDisplayName(TextStyle.FULL, Locale.FRENCH);
                String label = moisString + " " + annee;
                data.put(label, total);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return data;
    }
}
