/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import util.ConnexionSingleton;
import util.Hash;

/**
 *
 * @author HP
 */
public class LoginDao {

    public boolean login(String nom, String password) {
        try {
            Connection connecxion = ConnexionSingleton.getInstace().getConnection();
            String sql = "SELECT password FROM login WHERE nom = ?";
            PreparedStatement ps = connecxion.prepareStatement(sql);
            ps.setString(1, nom);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String dbHash = rs.getString("password");
                String programHash = Hash.md5(password);
                return dbHash.equals(programHash);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
